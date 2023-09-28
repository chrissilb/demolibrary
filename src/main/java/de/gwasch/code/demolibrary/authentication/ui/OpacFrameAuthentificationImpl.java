package de.gwasch.code.demolibrary.authentication.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import de.gwasch.code.demolibrary.interfaces.authentication.ui.LoginDialog;
import de.gwasch.code.demolibrary.interfaces.authentication.ui.OpacFrameAuthentification;
import de.gwasch.code.demolibrary.interfaces.library.ui.OpacFrame;
import de.gwasch.code.escframework.components.annotations.Core;
import de.gwasch.code.escframework.components.annotations.Extension;
import de.gwasch.code.escframework.components.annotations.PatternControlMethod;
import de.gwasch.code.escframework.components.annotations.Thiz;
import de.gwasch.code.escframework.components.annotations.Tick;
import de.gwasch.code.escframework.components.annotations.Within;
import de.gwasch.code.escframework.components.events.InvocationEvent;
import de.gwasch.code.escframework.components.utils.InstanceAllocator;
import de.gwasch.code.escframework.events.patterns.CompareCondition;
import de.gwasch.code.escframework.events.patterns.Rule;
import de.gwasch.code.escframework.events.patterns.RuleBuilder;
import de.gwasch.code.escframework.events.patterns.RuleMode;
import de.gwasch.code.escframework.events.utils.TimeUtil;
import de.gwasch.code.escframework.states.states.SimpleState;

@Extension(type=OpacFrameAuthentification.class, extendz=OpacFrame.class)
@PatternControlMethod(methodName="startLowTrafficObservation")
@PatternControlMethod(methodName="stopLowTrafficObservation")
@PatternControlMethod(methodName="startHighTrafficObservation")
@PatternControlMethod(methodName="stopHighTrafficObservation")
public class OpacFrameAuthentificationImpl {

	@Thiz
	private OpacFrameAuthentification thiz;
	
	@Core
	private OpacFrame core;
	
	private JMenuItem miLogin;
	private JMenuItem miLogout;
	
	private JLabel lblUserStatus;
	private int countdownCounter;
	
	public void initView() {
				
		InvocationEvent actionEvent;
		
		RuleBuilder rb = new RuleBuilder()
		.name("lowTraffic")
		.triggerInterval(5000)
		.maxTriggerCount(0)
		.triggerCountCompareCondition(CompareCondition.LE_AFTER)
		.triggerPatternEvent(new InvocationEvent() {
			public boolean equals(Object obj) {
				return obj instanceof InvocationEvent;
			}
		})
		.actionEvent(actionEvent = InstanceAllocator.createInvocationEvent(thiz, "startAutoLogout"))
		.actionFinishEvent(InstanceAllocator.createReturnEvent(thiz, actionEvent))
		.activatePatternEvent(InstanceAllocator.createInvocationEvent(thiz, "startLowTrafficObservation"))
		.deactivatePatternEvent(InstanceAllocator.createInvocationEvent(thiz, "stopLowTrafficObservation"));
		InstanceAllocator.getPatternMatcher().addRule(rb.toRule());	
		
		
		InvocationEvent tickActivateEvent = InstanceAllocator.createInvocationEvent(thiz, "startCountdown");
		Rule tickRule = InstanceAllocator.getPatternMatcher().getRuleByActivatePatternEvent(tickActivateEvent);
		final SimpleState<RuleMode> tickRuleMode = tickRule.getRuleModeState();

		rb = new RuleBuilder()
		.name("highTraffic")
		.triggerInterval(2000000)
		.maxTriggerCount(0)
		.triggerCountCompareCondition(CompareCondition.ONCE_MT)
		.triggerPatternEvent(new InvocationEvent() {			
			public boolean equals(Object obj) {
				boolean ret = obj instanceof InvocationEvent;
				ret &= tickRuleMode.getValue() == RuleMode.ACTIVE;
				ret &= EventQueue.getCurrentEvent() instanceof InputEvent;
				return ret;
			}
		})
		.actionEvent(actionEvent = InstanceAllocator.createInvocationEvent(thiz, "stopAutoLogout"))
		.actionFinishEvent(InstanceAllocator.createReturnEvent(thiz, actionEvent))
		.activatePatternEvent(InstanceAllocator.createInvocationEvent(thiz, "startHighTrafficObservation"))
		.deactivatePatternEvent(InstanceAllocator.createInvocationEvent(thiz, "stopHighTrafficObservation"));
		InstanceAllocator.getPatternMatcher().addRule(rb.toRule());
		
		
		core.initView();
		
		core.getFileMenu().insert(miLogin = new JMenuItem("Login"), 0);
		miLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				thiz.login();
			}
		});

		core.getFileMenu().insert(miLogout = new JMenuItem("Logout"), 1);
		miLogout.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		
		core.getFileMenu().insertSeparator(2);
		
		
		JPanel statuspane = new JPanel();
		statuspane.setLayout(new BoxLayout(statuspane, BoxLayout.X_AXIS));
		statuspane.setPreferredSize(new Dimension(core.getOpacFrameImpl().getWidth(), 20));
		statuspane.setBorder(new BevelBorder(BevelBorder.LOWERED));
		lblUserStatus = new JLabel("status");
		statuspane.add(lblUserStatus);
	
		core.getOpacFrameImpl().add(BorderLayout.SOUTH, statuspane);
		
		
	}
	
	
	public void updateView() {
		core.updateView();
		
		if (core.getUser() == null) {
			lblUserStatus.setText("");
		}
		else {
			lblUserStatus.setText(core.getUser().getName() + " is logged in.");
		}

		miLogin.setEnabled(core.getUser() == null);
		miLogout.setEnabled(core.getUser() != null);
	}
	
//	@After(interval=10000, methodName="after2")	
//	@After(interval=1000, methodName="after1")
	@Within(interval=5000, methodName="loginSupport")
	public boolean login() {
//		thiz.delay();
//		LoginDialogImpl ld = new LoginDialogImpl(core.getOpacFrameImpl(), core.getLibrary());
		LoginDialog ld = InstanceAllocator.create(LoginDialog.class, core.getOpacFrameImpl(), core.getLibrary());
		ld.init();
		
		boolean success = ld.getUser() != null;
		if (success) {
			core.setUser(ld.getUser());
			thiz.startLowTrafficObservation();
		}
		
		thiz.updateView();
		
		return success;
	}
	
	public void loginSupport() {
//		System.out.println(InstanceAllocator.getInvocationPN().networkToString());
		System.out.println("loginSupport, " + TimeUtil.timestamp());
		lblUserStatus.setText("Please enter username and password.");
	}
	
	private void logout() {
		thiz.stopHighTrafficObservation();
		thiz.stopLowTrafficObservation();
		thiz.stopCountdown();
		core.setUser(null);
		updateView();
	}

	public void startAutoLogout() {
		assert core.getUser() != null;
		thiz.stopLowTrafficObservation();
		countdownCounter = 30;
		thiz.startCountdown();
		thiz.startHighTrafficObservation();
	}
	
	public void stopAutoLogout() {
		assert core.getUser() != null;
		thiz.stopHighTrafficObservation();
		thiz.stopCountdown();
		thiz.startLowTrafficObservation();
		thiz.updateView();
	}
	
//	@Tick(interval=1000, invocations=30)
//	@Tick2(interval=1000, invocations=30, activateMethod="startCountdown", deactivateMethod="stopCountdown")
	@Tick(interval=1000, invocations=30, activateMethod="startCountdown", deactivateMethod="stopCountdown")
	public void doCountdown() {
//		System.out.println("doCountdown, " + TimeUtil.timestamp());		

		countdownCounter--;

		if (countdownCounter > 0) {
			lblUserStatus.setText("Automatic logout in " + countdownCounter + " seconds.");
		}
		else {
			logout();
		}
	}
	
	public void after1() {
		System.out.println("after1, " + TimeUtil.timestamp());
	}
	
	public void after2() {
		System.out.println("after2, " + TimeUtil.timestamp());
	}
	
//	@Delay(interval=2000)
	public void delay() {
		System.out.println("delay, " + TimeUtil.timestamp());
//		thiz.delay();
	}
	
}

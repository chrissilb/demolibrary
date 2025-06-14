package de.gwasch.code.demolibrary.authentication.ui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import de.gwasch.code.demolibrary.interfaces.authentication.model.UserAuthentification;
import de.gwasch.code.demolibrary.interfaces.authentication.ui.LoginDialog;
import de.gwasch.code.demolibrary.interfaces.library.model.Library;
import de.gwasch.code.escframework.components.annotations.Service;
import de.gwasch.code.escframework.components.annotations.Thiz;


@Service(type=LoginDialog.class)
public class LoginDialogImpl extends JDialog {

	private static final long serialVersionUID = 1L;

	@Thiz
	private LoginDialog thiz;
	
	private Library library;
	private UserAuthentification user;
	
	private JTextField txtUser;
	private JPasswordField txtPassword;

	public LoginDialogImpl(JFrame parent) {
		super(parent);

		setLocation(parent.getX() + 30, parent.getY() + 30);
	}
	
	public void init(Library library) {
		this.library = library;
		this.user = null;
		
		setModal(true);
		setTitle("Login");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		setLayout(new GridLayout(3, 2));
		
		add(new JLabel("User:"));
		add(txtUser = new JTextField());

		add(new JLabel("Password:"));
		add(txtPassword = new JPasswordField());

		JButton cmdlogin = new JButton("Login");
		getRootPane().setDefaultButton(cmdlogin);
		add(cmdlogin);
		JButton cmdcancel = new JButton("Cancel");
		add(cmdcancel);
		
		cmdlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thiz.login();
			}
		});
		
		ActionListener cancelHandler = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		};
		
		cmdcancel.addActionListener(cancelHandler);
		
		getRootPane().registerKeyboardAction(cancelHandler, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		pack();
		setVisible(true);
	}

	public UserAuthentification getUser() {
		return user;
	}
	
//	@More(interval=1000000, invocations=3, methodName="sleep")
//	@Less(interval=5000, invocations=1, methodName="hurryUp")
	public void login() {	
		UserAuthentification user = (UserAuthentification)library.findUser(txtUser.getText());
		
		if (user == null) {
			JOptionPane.showMessageDialog(LoginDialogImpl.this, "Invalid Username.", "Error", 
					JOptionPane.ERROR_MESSAGE);
			txtUser.requestFocusInWindow();
			txtUser.selectAll();
			return;
		}
		
		if (!user.getPassword().equals(new String(txtPassword.getPassword()))) {
			
			JOptionPane.showMessageDialog(LoginDialogImpl.this, "Invalid Password.", "Error", 
					JOptionPane.ERROR_MESSAGE);
			txtPassword.requestFocusInWindow();
			txtPassword.selectAll();
			return;
		}
		
		this.user = user;
		dispose();
	}
	
	public void sleep() {
		System.out.println("sleep");
	}	
	
	public void hurryUp() {
		System.out.println("hurry up");
	}
		
	public void cancel() {
		dispose();
	}
}
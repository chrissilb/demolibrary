package de.gwasch.code.demolibrary.authorization.ui;

import javax.swing.JOptionPane;

import de.gwasch.code.demolibrary.interfaces.authorization.ui.OpacFrameAuthorization;
import de.gwasch.code.demolibrary.interfaces.library.ui.OpacFrame;
import de.gwasch.code.escframework.components.annotations.Core;
import de.gwasch.code.escframework.components.annotations.Extension;
import de.gwasch.code.escframework.components.annotations.Thiz;

@Extension(type=OpacFrameAuthorization.class, extendz=OpacFrame.class)
public class OpacFrameAuthorizationImpl {

	@Thiz
	private OpacFrameAuthorization thiz;
	
	@Core
	private OpacFrame core;
	
	public void showError() {
		
		JOptionPane.showMessageDialog(core.getOpacFrameImpl(), "Already lent one book.", "Error", 
				JOptionPane.ERROR_MESSAGE);
	}
	
	public void showLateError() {
		
		JOptionPane.showMessageDialog(core.getOpacFrameImpl(), "Please return your current book to the library, first.", "Error", 
				JOptionPane.ERROR_MESSAGE);
	}
}

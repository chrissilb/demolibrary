package de.gwasch.code.demolibrary.authorization.model;

import java.lang.reflect.Method;
import java.util.Arrays;

import de.gwasch.code.demolibrary.interfaces.authentication.model.LibraryAuthentification;
import de.gwasch.code.demolibrary.interfaces.authorization.model.LibraryAuthorization;
import de.gwasch.code.demolibrary.interfaces.authorization.ui.OpacFrameAuthorization;
import de.gwasch.code.demolibrary.interfaces.library.model.Book;
import de.gwasch.code.demolibrary.interfaces.library.model.Library;
import de.gwasch.code.demolibrary.interfaces.library.model.Student;
import de.gwasch.code.demolibrary.interfaces.library.model.User;
import de.gwasch.code.escframework.components.annotations.Client;
import de.gwasch.code.escframework.components.annotations.Core;
import de.gwasch.code.escframework.components.annotations.Extension;
import de.gwasch.code.escframework.components.annotations.Thiz;

@Extension(type=LibraryAuthorization.class, client=OpacFrameAuthorization.class, extendz=Library.class, after=LibraryAuthentification.class)
public class LibraryAuthorizationImpl {
	
	@Client
	private OpacFrameAuthorization client;
	
	@Thiz //todo, wieder löschen
	private LibraryAuthorization thiz;
	
	@Core
	private Library core;
	
	private int trials;
	private User lastUser;
	
	public LibraryAuthorizationImpl() {
		trials = 0;
		lastUser = null;
	}
	
	public boolean lendBook(User user, Book book) {
		lastUser = null;
		return core.lendBook(user, book);
	}
	
	public boolean lendBook(Student user, Book book) {

		if (user != lastUser) {
			trials = 0;
			lastUser = user;
		}
		
		if (core.getLendings(user).size() > 0) {
			trials++;
			
			if (trials < 3) {
				client.showError();
			}
			else {
				client.showLateError();
			}
			return false;
		}
		else {
			trials = 0;
			return core.lendBook(user, book);
		}
	}
	
//	@Asterisk(type=AsteriskType.AFTER_ELSE)
	public void ASTERISK() {
		System.out.println("LibraryAuthorization.asterisk");
	}
	
//	@Asterisk(type=AsteriskType.AFTER_ELSE)
	public void ASTERISK(Object proxy, Method method, Object[] args) {
		System.out.println("asterisk: " + method + ", " + Arrays.toString(args));
		thiz.ASTERISK();
	}
}

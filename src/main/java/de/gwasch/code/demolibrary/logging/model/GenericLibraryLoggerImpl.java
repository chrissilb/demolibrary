package de.gwasch.code.demolibrary.logging.model;

import java.lang.reflect.Method;

import de.gwasch.code.demolibrary.interfaces.authorization.model.LibraryAuthorization;
import de.gwasch.code.demolibrary.interfaces.library.model.Library;
import de.gwasch.code.demolibrary.interfaces.logging.model.GenericLibraryLogger;
import de.gwasch.code.demolibrary.interfaces.logging.model.LogBook;
import de.gwasch.code.demolibrary.interfaces.logging.model.LogUser;
import de.gwasch.code.escframework.components.annotations.Asterisk;
import de.gwasch.code.escframework.components.annotations.Extension;

@Extension(type=GenericLibraryLogger.class, extendz=Library.class, after=LibraryAuthorization.class)
public class GenericLibraryLoggerImpl {

	@Asterisk
	public void ASTERISK(Object proxy, Method method, Object[] args) {
		
		if (method.getName().equals("lendBook")) {
			LogUser logUser = (LogUser)args[0];
			LogBook logBook = (LogBook)args[1];
			System.out.println(logUser.getLogString() + " lends " + logBook.getLogString());
		}
	}
}

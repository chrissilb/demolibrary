package de.gwasch.code.demolibrary.logging.model;

import de.gwasch.code.demolibrary.interfaces.authorization.model.LibraryAuthorization;
import de.gwasch.code.demolibrary.interfaces.library.model.Library;
import de.gwasch.code.demolibrary.interfaces.logging.model.LibraryLogger;
import de.gwasch.code.demolibrary.interfaces.logging.model.LogBook;
import de.gwasch.code.demolibrary.interfaces.logging.model.LogUser;
import de.gwasch.code.escframework.components.annotations.Core;
import de.gwasch.code.escframework.components.annotations.Extension;

@Extension(type=LibraryLogger.class, extendz=Library.class, after=LibraryAuthorization.class)
public class LibraryLoggerImpl {
	
	@Core
	private Library core;
	
	public boolean lendBook(LogUser user, LogBook book) {

		if (!core.lendBook(user, book)) return false;

		StringBuffer sb = new StringBuffer();
		sb.append(user.getLogString());
		sb.append(" lends ");
		sb.append(book.getLogString());
		System.out.println(sb);
		return true;
	}
	
//	public boolean lendBook(LogStudent user, LogBook book) {
//
//		if (!core.lendBook(user, book)) return false;
//
//		StringBuffer sb = new StringBuffer();
//		sb.append(user.getLogString());
//		sb.append(" lends ");
//		sb.append(book.getLogString());
//		System.out.println(sb);
//		return true;
//	}
}

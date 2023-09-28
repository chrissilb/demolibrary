package de.gwasch.code.demolibrary.logging.model;

import de.gwasch.code.demolibrary.interfaces.library.model.Book;
import de.gwasch.code.demolibrary.interfaces.logging.model.LogBook;
import de.gwasch.code.escframework.components.annotations.Core;
import de.gwasch.code.escframework.components.annotations.Extension;

@Extension(type=LogBook.class, extendz=Book.class)
public class LogBookImpl {

	@Core
	private Book book;
	
	public String getLogString() {
		return "Book (title: " + book.getTitle() + ")";
	}
}

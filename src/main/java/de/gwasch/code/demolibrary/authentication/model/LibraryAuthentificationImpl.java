package de.gwasch.code.demolibrary.authentication.model;


import de.gwasch.code.demolibrary.interfaces.authentication.model.LibraryAuthentification;
import de.gwasch.code.demolibrary.interfaces.authentication.ui.OpacFrameAuthentification;
import de.gwasch.code.demolibrary.interfaces.library.model.Book;
import de.gwasch.code.demolibrary.interfaces.library.model.Library;
import de.gwasch.code.demolibrary.interfaces.library.model.User;
import de.gwasch.code.escframework.components.annotations.Client;
import de.gwasch.code.escframework.components.annotations.Core;
import de.gwasch.code.escframework.components.annotations.Extension;

@Extension(type=LibraryAuthentification.class, client=OpacFrameAuthentification.class, extendz=Library.class)
public class LibraryAuthentificationImpl {

	@Client
	private OpacFrameAuthentification client;
	
	@Core
	private Library core;
	
	public boolean lendBook(User user, Book book) {
		if (client.getUser() == null && !client.login()) {
			return false;
		}
		
		return core.lendBook(client.getUser(), book);
	}
}

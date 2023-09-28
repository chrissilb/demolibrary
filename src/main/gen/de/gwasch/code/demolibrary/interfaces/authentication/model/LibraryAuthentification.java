package de.gwasch.code.demolibrary.interfaces.authentication.model;

import de.gwasch.code.demolibrary.interfaces.library.model.Book;
import de.gwasch.code.demolibrary.interfaces.library.model.Library;
import de.gwasch.code.demolibrary.interfaces.library.model.User;

public interface LibraryAuthentification extends Library {

    boolean lendBook(User user, Book book);
}

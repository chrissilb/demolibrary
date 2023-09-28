package de.gwasch.code.demolibrary.interfaces.authorization.model;

import de.gwasch.code.demolibrary.interfaces.library.model.Book;
import de.gwasch.code.demolibrary.interfaces.library.model.Library;
import de.gwasch.code.demolibrary.interfaces.library.model.Student;
import de.gwasch.code.demolibrary.interfaces.library.model.User;
import java.lang.reflect.Method;

public interface LibraryAuthorization extends Library {

    boolean lendBook(User user, Book book);

    boolean lendBook(Student user, Book book);

    void ASTERISK();

    void ASTERISK(Object proxy, Method method, Object[] args);
}

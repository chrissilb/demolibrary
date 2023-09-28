package de.gwasch.code.demolibrary.interfaces.library.model;

import java.util.List;

public interface Library {

    void addBook(Book book);

    List<Book> getBooks();

    List<Book> findBook(String pattern);

    User findUser(String name);

    void addUser(User user);

    boolean lendBook(User user, Book book);

    boolean isLent(Book book);

    List<Lending> getLendings(User user);

    String toString();
}

package de.gwasch.code.demolibrary.interfaces.library.model;

import java.util.Date;

public interface Lending {

    void init(User user, Book book, Date time);

    User getUser();

    void setUser(User user);

    Book getBook();

    void setBook(Book book);

    Date getTime();

    void setTime(Date time);

    String toString();
}

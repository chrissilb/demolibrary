package de.gwasch.code.demolibrary.interfaces.library.model;

public interface Book {

    void init(int id, String title);

    int getId();

    void setId(int id);

    String getTitle();

    void setTitle(String title);

    boolean equals(Object obj);

    String toString();
}

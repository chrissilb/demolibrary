package de.gwasch.code.demolibrary.interfaces.library.model;

public interface User {

    void init(int id, String name);

    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    boolean equals(Object obj);

    String toString();
}

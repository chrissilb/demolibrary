package de.gwasch.code.demolibrary.interfaces.library.model;

public interface Student extends User {

    void init(int studentid);

    int getStudentId();

    void setStudentId(int studentid);

    String toString();
}

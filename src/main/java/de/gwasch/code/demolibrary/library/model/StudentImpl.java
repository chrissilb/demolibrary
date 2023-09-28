package de.gwasch.code.demolibrary.library.model;


import de.gwasch.code.demolibrary.interfaces.library.model.Student;
import de.gwasch.code.demolibrary.interfaces.library.model.User;
import de.gwasch.code.escframework.components.annotations.Base;
import de.gwasch.code.escframework.components.annotations.Service;

@Service(type=Student.class, inherits=User.class)
public class StudentImpl {

	private int studentId;
	
	@Base
	private User base;

	public void init(int studentid) {
		studentId = studentid;
	}
	
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentid) {
		studentId = studentid;
	}
	
	public String toString() {
		return "Student (studentId: " + studentId + "), " + base.toString();
	}
}

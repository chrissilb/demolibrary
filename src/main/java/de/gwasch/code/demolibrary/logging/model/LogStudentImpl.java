package de.gwasch.code.demolibrary.logging.model;

import de.gwasch.code.demolibrary.interfaces.library.model.Student;
import de.gwasch.code.demolibrary.interfaces.logging.model.LogStudent;
import de.gwasch.code.escframework.components.annotations.Core;
import de.gwasch.code.escframework.components.annotations.Extension;

@Extension(type=LogStudent.class, extendz=Student.class)
//@Extension(type=LogStudent.class, inherits=LogUser.class, extendz=Student.class)
public class LogStudentImpl {

	@Core
	private Student student;
	
	public String getLogString() {
		return "Student (name: " + student.getName() + ")";
	}
}

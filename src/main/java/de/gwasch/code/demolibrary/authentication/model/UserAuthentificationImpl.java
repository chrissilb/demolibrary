package de.gwasch.code.demolibrary.authentication.model;


import de.gwasch.code.demolibrary.interfaces.authentication.model.UserAuthentification;
import de.gwasch.code.demolibrary.interfaces.library.model.User;
import de.gwasch.code.escframework.components.annotations.Extension;

@Extension(type=UserAuthentification.class, extendz=User.class)
public class UserAuthentificationImpl {

	private String password;

	public void init(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

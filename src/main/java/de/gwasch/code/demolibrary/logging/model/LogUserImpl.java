package de.gwasch.code.demolibrary.logging.model;

import de.gwasch.code.demolibrary.interfaces.library.model.User;
import de.gwasch.code.demolibrary.interfaces.logging.model.LogUser;
import de.gwasch.code.escframework.components.annotations.Core;
import de.gwasch.code.escframework.components.annotations.Extension;

@Extension(type=LogUser.class, extendz=User.class)
public class LogUserImpl {

	@Core
	private User user;
	
	public String getLogString() {
		return "User (name: " + user.getName() + ")";
	}
}

package de.gwasch.code.demolibrary.library.model;


import de.gwasch.code.demolibrary.interfaces.library.model.User;
import de.gwasch.code.escframework.components.annotations.Service;

@Service(type=User.class)
public class UserImpl {

	private int id;
	private String name;

	public void init(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean equals(Object obj) {
		User cmp = (User)obj;
		return id == cmp.getId();
	}
	
	public String toString() {
		return "User (id: " + id + ", name: " + name + ")";
	}
}

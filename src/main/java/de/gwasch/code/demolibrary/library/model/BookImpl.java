package de.gwasch.code.demolibrary.library.model;



import de.gwasch.code.demolibrary.interfaces.library.model.Book;
import de.gwasch.code.escframework.components.annotations.Service;

@Service(type=Book.class)
public class BookImpl {
	
	private int id;
	private String title;

	public void init(int id, String title) {
		this.id = id;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean equals(Object obj) {
		Book cmp = (Book)obj;
		return id == cmp.getId();
	}
	
	public String toString() {
		return "Book (id: " + id + ", title: " + title + ")";
	}
}

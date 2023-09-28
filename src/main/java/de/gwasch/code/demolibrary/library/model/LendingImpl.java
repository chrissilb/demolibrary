package de.gwasch.code.demolibrary.library.model;



import java.util.Date;

import de.gwasch.code.demolibrary.interfaces.library.model.Book;
import de.gwasch.code.demolibrary.interfaces.library.model.Lending;
import de.gwasch.code.demolibrary.interfaces.library.model.User;
import de.gwasch.code.escframework.components.annotations.Service;

@Service(type=Lending.class)
public class LendingImpl {

	private User user;
	private Book book;
	private Date time;

	public void init(User user, Book book, Date time) {
		this.user = user;
		this.book = book;
		this.time = time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	public String toString() {
		return "Lending (user: " + user.getName() + ", book: " + book.getTitle() + ")";
	}
}

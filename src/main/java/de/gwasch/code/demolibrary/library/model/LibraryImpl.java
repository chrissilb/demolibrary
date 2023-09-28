package de.gwasch.code.demolibrary.library.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.gwasch.code.demolibrary.interfaces.library.model.Book;
import de.gwasch.code.demolibrary.interfaces.library.model.Lending;
import de.gwasch.code.demolibrary.interfaces.library.model.Library;
import de.gwasch.code.demolibrary.interfaces.library.model.User;
import de.gwasch.code.escframework.components.annotations.Service;
import de.gwasch.code.escframework.components.utils.InstanceAllocator;

@Service(type=Library.class)
public class LibraryImpl {

	private List<Book> books;
	private List<User> users;
	private List<Lending> lendings;
	
	public LibraryImpl() {
		books = new ArrayList<Book>();
		users = new ArrayList<User>();
		lendings = new ArrayList<Lending>();
	}
	
	public void addBook(Book book) {
		books.add(book);
	}

	public List<Book> getBooks() {		
		return books;
	}
	
	public List<Book> findBook(String pattern) {
		List<Book> results = new ArrayList<Book>();
		Pattern p = Pattern.compile(".*" + pattern + ".*", Pattern.CASE_INSENSITIVE);
		
		for (Book book : books) {
			Matcher m = p.matcher(book.getTitle());
			
			if (m.matches()) {
				results.add(book);
			}
		}
		
		return results;
	}
	
	public User findUser(String name) {
		for (User user : users) {
			if (user.getName().equals(name)) {
				return user;
			}
		}
		
		return null;
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public boolean lendBook(User user, Book book) {
		
		if (isLent(book)) return false;
		
		Lending lending = InstanceAllocator.create(Lending.class);
		lending.init(user, book, new Date());
		lendings.add(lending);
		return true;
	}
	
	public boolean isLent(Book book) {
		for (Lending lending : lendings) {
			if (lending.getBook().equals(book)) return true;
		}
		
		return false;
	}
	
	public List<Lending> getLendings(User user) {
		List<Lending> userlendings = new ArrayList<Lending>();
		
		for (Lending lending : lendings) {
			if (lending.getUser().equals(user)) {
				userlendings.add(lending);
			}
		}
		
		return userlendings;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Books:\n");
		for (Book b : books) {
			sb.append("- " + b.toString() + '\n');
		}
		sb.append("\nUsers:\n");
		for (User u : users) {
			sb.append("- " + u.toString() + '\n');
		}
		sb.append("\nLendings:\n");
		for (Lending l : lendings) {
			sb.append("- " + l.toString() + '\n');
		}
		
		return sb.toString();
	}
}

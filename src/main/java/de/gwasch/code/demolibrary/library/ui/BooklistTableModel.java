package de.gwasch.code.demolibrary.library.ui;

import de.gwasch.code.demolibrary.interfaces.library.model.Book;
import de.gwasch.code.demolibrary.interfaces.library.model.Library;

import javax.swing.table.AbstractTableModel;

public class BooklistTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private Library library;

	public BooklistTableModel(Library library) {
		this.library = library;
	}

	public String getColumnName(int col) {

		switch (col) {
			case 0: return "Id";
			case 1: return "Title";
			default: return null;
		}
	}

	public int getRowCount() {
		return library.getBooks().size();
	}

	public int getColumnCount() {
		return 2;
	}

	public Object getValueAt(int row, int col) {

		Book book = (Book)library.getBooks().get(row);
		switch (col) {
			case 0: return book.getId();
			case 1: return book.getTitle();
			default: return null;
		}
	}
}
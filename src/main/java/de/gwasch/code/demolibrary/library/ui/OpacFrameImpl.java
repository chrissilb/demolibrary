package de.gwasch.code.demolibrary.library.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import de.gwasch.code.demolibrary.interfaces.library.model.Book;
import de.gwasch.code.demolibrary.interfaces.library.model.Library;
import de.gwasch.code.demolibrary.interfaces.library.model.User;
import de.gwasch.code.demolibrary.interfaces.library.ui.OpacFrame;
import de.gwasch.code.escframework.components.annotations.Service;
import de.gwasch.code.escframework.components.annotations.Thiz;
import de.gwasch.code.escframework.components.utils.InstanceAllocator;

@Service(type=OpacFrame.class)
public class OpacFrameImpl extends JFrame {

	private static final long serialVersionUID = 1L;
	
	@Thiz
	private OpacFrame thiz;
	
	private Library library;
	private Book selectedBook;
	private User user;
	
	private JMenu fileMenu;
	
	private ListSelectionModel bookSelectionModel;
	private BooklistTableModel booklistTableModel;
	
	private JLabel lblId;
	private JLabel lblTitle;
	private JLabel lblBookStatus;
	private JButton cmdLend;

	
	public void init(Library library) {
		
		this.library = library;
		selectedBook = library.getBooks().get(0);
		user = null;
		
		thiz.initView();
		thiz.updateView();

		pack();
//		setVisible(true);		NOTE: set visible() is not allowed within components
	}
	
	//@After(interval=10000, methodName="help")
	public void initView() {
		
		setTitle("OPAC");
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
			
		
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		JMenuItem mi;

		menubar.add(fileMenu = new JMenu("File"));
		
		fileMenu.add(mi = new JMenuItem("Exit"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		
		
		booklistTableModel = new BooklistTableModel(library);
		JTable table = new JTable(booklistTableModel);
		bookSelectionModel = table.getSelectionModel();
		bookSelectionModel.setSelectionInterval(0, 0);
		bookSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {	
				if (event.getClickCount() != 2) return;
				thiz.selectBook();	
			}
		});
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		
		table.getColumnModel().getColumn(1).setPreferredWidth(170);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(table);
		JPanel titlePane = new JPanel();
		titlePane.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder(), null));
		titlePane.setLayout(new BorderLayout());
		titlePane.add(scrollPane);
		titlePane.setPreferredSize(new Dimension(250, 130));

		add(BorderLayout.WEST, titlePane);
		
		JPanel detailsPane = new JPanel();
		detailsPane.setLayout(null);
		detailsPane.setPreferredSize(new Dimension(250, 130));
		
		JLabel label = new JLabel("Id:");
		label.setBounds(10, 10, 70, 20);
		detailsPane.add(label);
		
		lblId = new JLabel();
		lblId.setBounds(80, 10, 200, 20);
		detailsPane.add(lblId);
		
		label = new JLabel("Title:");
		label.setBounds(10, 30, 70, 20);
		detailsPane.add(label);
		
		lblTitle = new JLabel();
		lblTitle.setBounds(80, 30, 200, 20);
		detailsPane.add(lblTitle);
		
		label = new JLabel("Status:");
		label.setBounds(10, 50, 70, 20);
		detailsPane.add(label);
		
		lblBookStatus = new JLabel();
		lblBookStatus.setBounds(80, 50, 200, 20);
		detailsPane.add(lblBookStatus);

		JPanel pane = new JPanel();
		pane.setBounds(0, 70, 300, 40);
		pane.setLayout(new FlowLayout(FlowLayout.LEFT));

		cmdLend = new JButton("Lend");
		pane.add(cmdLend);
		detailsPane.add(pane);
		
		cmdLend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thiz.lendBook();
			}
		});
		
		add(BorderLayout.EAST, detailsPane);

	}
	
	public void help() {
		System.out.println("help");
	}
	
	private void exit() {
//		InstanceAllocator.delete();
		InstanceAllocator.getTimerPN().deactivate();
		dispose();
	}
	
	public JMenu getFileMenu() {
		return fileMenu;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Library getLibrary() {
		return library;
	}
	
	public void lendBook() {
		library.lendBook(user, selectedBook);
		thiz.updateView();
	}
	
	
	public void selectBook() {
		int row = bookSelectionModel.getMinSelectionIndex();
		if (row < 0) row = 0;
		selectedBook = (Book)library.getBooks().get(row);
		
		thiz.updateView();
	}
	
	
	public void updateView() {
			
		lblId.setText(""+selectedBook.getId());
		lblTitle.setText(selectedBook.getTitle());
		
		if (library.isLent(selectedBook)) {
			lblBookStatus.setText("lent");
			cmdLend.setEnabled(false);
		}
		else {
			lblBookStatus.setText("available");
			cmdLend.setEnabled(true);
		}
	}
}

package de.gwasch.code.demolibrary;

import de.gwasch.code.demolibrary.interfaces.authentication.model.UserAuthentification;
import de.gwasch.code.demolibrary.interfaces.library.model.Book;
import de.gwasch.code.demolibrary.interfaces.library.model.Library;
import de.gwasch.code.demolibrary.interfaces.library.model.Student;
import de.gwasch.code.demolibrary.interfaces.library.model.User;
import de.gwasch.code.demolibrary.interfaces.library.ui.OpacFrame;
import de.gwasch.code.escframework.components.utils.InstanceAllocator;

public class App {
	
	private static Library library = null;
	
	public static void init() {
		
		library = InstanceAllocator.create(Library.class);
		
		Book bibel = InstanceAllocator.create(Book.class);
		bibel.init(1, "Bibel");
		library.addBook(bibel);
		
		Book koran = InstanceAllocator.create(Book.class);
		koran.init(2, "Koran");
		library.addBook(koran);

		Book manifest = InstanceAllocator.create(Book.class);
		manifest.init(3, "Kommunistisches Manifest");
		library.addBook(manifest);

		
		Student karl = InstanceAllocator.create(Student.class);
		karl.setId(1);
		karl.setName("Karl");
		UserAuthentification uaKarl = ((UserAuthentification)karl);
		uaKarl.setPassword("geheim");
		library.addUser(karl);
		
		User mohammed = InstanceAllocator.create(User.class);
		mohammed.setId(2);
		mohammed.setName("Mohammed");
		((UserAuthentification)mohammed).setPassword("geheim");
		library.addUser(mohammed);
		
		Student jesus = InstanceAllocator.create(Student.class);		
		jesus.setId(3);
		jesus.setName("Jesus");
		((UserAuthentification)jesus).setPassword("geheim");
		library.addUser(jesus);
	}
	
	
	public static void main(String[] args) throws Exception {
		
		InstanceAllocator.collectTypes();
		
		init();
		
		OpacFrame opacFrame = InstanceAllocator.create(OpacFrame.class);
		
		opacFrame.init(library);
		opacFrame.getOpacFrameImpl().setVisible(true);
	}
}

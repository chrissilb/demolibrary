package de.gwasch.code.demolibrary.interfaces.logging.model;

import de.gwasch.code.demolibrary.interfaces.library.model.Library;

public interface LibraryLogger extends Library {

    boolean lendBook(LogUser user, LogBook book);
}

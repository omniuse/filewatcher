package de.h2o.filewatcher;

/*
 * This seems to be the way, how to watch paths and files in java, using Java 7 with nio.2 library
 * 
 * http://stackoverflow.com/questions/16251273/can-i-watch-for-single-file-change-with-watchservice-not-the-whole-directory
 * http://www.codeproject.com/Tips/560894/File-Change-Notification-in-Java
 */

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class FileChangeNotifier {

	private String nameOfFileToWatch;
	private String pathOfFileToWatch;

	/**
	 * Constructor checks, if the file to watch for changes is a file. <br>
	 * If so, 'pathOfFileToWatch' and 'nameOfFileToWatch' will be set. <br>
	 * Otherwise, an exception will be thrown.
	 */
	public FileChangeNotifier(File fileToWatch) throws Exception {
		if (!fileToWatch.isFile())
			throw new RuntimeException(fileToWatch + "is not a file!");

		// setting the path without the file name
		pathOfFileToWatch = fileToWatch.getAbsolutePath().substring(0, fileToWatch.getAbsolutePath().lastIndexOf(File.separator));
		nameOfFileToWatch = fileToWatch.getName();
	}

	/**
	 * Set the path and file, which has to be monitored for changes. <br>
	 * The monitoring will be finished, if the directory of the file has been deleted.
	 */
	public void watchDir(FileChangeListener listener) throws Exception {
		final WatchService service = FileSystems.getDefault().newWatchService(); // Create a WatchService
		final Path path = Paths.get(pathOfFileToWatch); // Get the directory to be monitored
		path.register(service, StandardWatchEventKinds.ENTRY_MODIFY); // Register the directory
		while (true) {
			final WatchKey key = service.take(); // retrieve the watchkey
			for (WatchEvent<?> event : key.pollEvents()) {
				final Path changed = (Path) event.context();
				if (changed.toString().equals(nameOfFileToWatch)) {
					listener.fileChanged(event); // the content of this event will be displayed in main
				}
			}
			boolean valid = key.reset();
			if (!valid) {
				break; // Exit if directory is deleted
			}
		}
	}

}

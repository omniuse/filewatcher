package de.h2o.filewatcher;

import java.nio.file.WatchEvent;

public interface FileChangeListener {
	void fileChanged(WatchEvent<?> event) throws Exception;
}

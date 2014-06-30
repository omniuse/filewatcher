package de.h2o.filewatcher;

import java.io.File;

public class AVParameterController extends ParameterController {

	private boolean continuousChanges = false;
	private File parameterFile = null;

	public AVParameterController(String[] args) {
		super(args);

		validate();

		if (hasParameter("-c")) {
			continuousChanges = true;
		}

		if (!hasFile()) {
			throw new RuntimeException("Keine gültige Datei gewählt");
		}
	}

	/**
	 * überprüft, ob<br>
	 * -Argumente übergeben wurden<br>
	 * -Datei im .txt oder .adocFormat vorliegt<br>
	 * -angegebene Datei existiert
	 * 
	 * setzt bei erfolgreicher Prüfung die variablen 'parameterFileName' und 'isFile'
	 * 
	 * 
	 * // * check if valid // * store following // ** fileName // ** continousChanges (bool)
	 * 
	 * @return true, wenn Überprüfung erfolgreich, sonst exception
	 */
	private void validate() {
		// (1) verify, if args has parameters -> no parameters = exception
		if (args.length == 0) {
			throw new RuntimeException("Keine Argumente übergeben!");
		}

		// (2) verify, if one parameter is either .txt or .adoc -> otherwise exception
		for (String arg : args) {
			if (arg.contains(".txt") || arg.contains(".adoc")) {
				// (2a) verify, if this file exists -> otherwise exception
				File file = new File(arg);
				if (!file.exists()) {
					throw new RuntimeException("Datei '" + file.getAbsolutePath() + "' existiert nicht");
				}
				parameterFile = file;
			}
		}
		if (!hasFile()) {
			throw new RuntimeException("Bitte '.txt' oder '.adoc' Datei auswählen.");
		}
	}

	/**
	 * returns true, if one parameter is a valid file
	 */
	private boolean hasFile() {
		if (parameterFile == null) {
			return false;
		}
		return true;
	}

	/**
	 * returns the file, which has to be watched.
	 * 
	 */
	public File getFile() {
		// constructor sets value for 'parameterFileName' (with 'isValid()'), otherwise exception
		return parameterFile;
	}

	public boolean isContinuousChangesEnabled() {
		if (!continuousChanges) {
			return false;
		}
		return true;
	}
}

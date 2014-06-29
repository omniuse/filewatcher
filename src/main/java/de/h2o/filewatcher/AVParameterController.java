package de.h2o.filewatcher;

import java.io.File;

public class AVParameterController extends ParameterController {

	public boolean continuousChanges = false;
	private File parameterFileName = null;
	private boolean isFile = false; // helper

	public AVParameterController(String[] args) {
		super(args);

		isValid();
		// * check if valid
		// * store following
		// ** fileName
		// ** continousChanges (bool)
		if (hasParameter("-c")) {
			continuousChanges = true;
		}

		if (!hasFile()) {
			throw new RuntimeException("Keine gültige Datei gewählt");
		}

		// Maik: nicht nötig, da in isValid() schon der filename gesetzt wird
		// if (hasFile()) {
		// fileName = getFile();
		// } else {
		// throw new RuntimeException("Keine Datei");
		// }

	}

	/**
	 * überprüft, ob<br>
	 * -Argumente übergeben wurden<br>
	 * -Datei im .txt oder .adocFormat vorliegt<br>
	 * -angegebene Datei existiert
	 * 
	 * setzt bei erfolgreicher Prüfung die variablen 'parameterFileName' und 'isFile'
	 * 
	 * @return true, wenn Überprüfung erfolgreich, sonst exception
	 */
	public boolean isValid() {
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
					throw new RuntimeException("Datei '" + file + "' existiert nicht");
				}
				parameterFileName = file;
				isFile = true;
			}
		}
		if (!isFile) {
			throw new RuntimeException("Bitte '.txt' oder '.adoc' Datei auswählen.");
		}
		return true;
	}

	private boolean hasParameter(String parameter) {
		for (String arg : args) {
			if (arg.equals("-c")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * returns true, if one parameter is a valid file
	 */
	private boolean hasFile() {
		if (isFile) {
			return true;
		}
		return false;
	}

	/**
	 * returns the file, which has to be watched.
	 * 
	 */
	public File getFile() {
		// constructor sets value for 'parameterFileName' (with 'isValid()'), otherwise exception
		return parameterFileName;
	}
}

package de.h2o.filewatcher;

import java.io.File;

public class ParameterHighLevel extends ParameterLowLevel {

	public ParameterHighLevel(String[] args) {
		super(args);
	}

	private File parameterFileName;

	/**
	 * check, if first parameter is a file and exists<br>
	 * 
	 * @return true, if parameter is a file and exists - otherwise false or an exception will be thrown.
	 */
	private boolean doesFileOfFirstParameterExists() {
		if (args.length > 0) {
			File file = new File(args[0]);

			if (file.exists()) {
				parameterFileName = file;
				return true;
			}

			if (!file.getName().contains(".")) {
				System.out.println(file + " ist keine datei");
				return false;
			}

			else {
				System.out.println("Datei existiert nicht");
				return false;
			}
		}
		throw new RuntimeException("Keine Argumente übergeben!");
	}

	/**
	 * check, if the second parameter is -c. <br>
	 * 
	 * @return true, if the second parameter is '-c' - otherwise an exception will be thrown.
	 */
	public boolean isSecondParameterC() {
		String errorMessage = "Um die Datei zu beobachten, muss der 2. Parameter '-c' sein.";
		if (args.length > 1) {

			if (args[1].equals("-c")) {
				return true;
			}

			System.out.println(errorMessage);
			throw new RuntimeException(errorMessage);
		}
		System.out.println("Kein 2. Parameter übergeben. " + errorMessage);
		throw new RuntimeException("Kein 2. Parameter übergeben. " + errorMessage);
	}

	/**
	 * check, if file type is either .txt or .adoc
	 * 
	 * @return true, if file type is either .txt or .adoc - otherwise an exception will be thrown.
	 */
	public boolean isValid() {
		if (doesFileOfFirstParameterExists()) {
			if ((parameterFileName.getName().endsWith(".txt") || parameterFileName.getName().endsWith(".adoc"))) {
				return true;
			}
		}
		throw new RuntimeException("Nur Daeien mit '.txt' oder '.adoc' sind erlaubt.");
	}

	/**
	 * Returns the file which has to watched.
	 * 
	 */
	public File getFileToWatch() {
		if (parameterFileName.equals("")) {
			throw new RuntimeException("keine Datei");
		}
		return parameterFileName;
	}
}

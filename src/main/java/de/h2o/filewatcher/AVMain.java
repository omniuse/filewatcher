package de.h2o.filewatcher;

import java.io.File;
import java.nio.file.WatchEvent;

public class AVMain implements FileChangeListener {

	private FileContent fileContent;
	File fileToWatch;

	/**
	 * Dateiinhalt wird ausgeben, bei Aufruf ohne Parameter werden Standardparameter übergeben<br>
	 * 1. Prameter = Dateiname und Pfad<br>
	 * => Dateiinhalt wird ausgegeben<br>
	 * 2. Parameter = "-c" (countinously)<br>
	 * => Datei wird überwacht und bei jeder Änderung wird der Dateiinhalt neu ausgegeben<br>
	 * 
	 * Exception wenn Parameter nicht korrekt<br>
	 * Exception wenn Datei nicht existiert<br>
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// run with default parameters
		if (args.length == 0) {
			String firstParameterDefault = "tmp/foo.txt";
			String secondParameterDefault = "-c";

			System.out.println("Ausführung mit den Standardparameten '" + firstParameterDefault + "' und '" + secondParameterDefault + "'.");
			args = new String[] { firstParameterDefault, secondParameterDefault };
		}

		new AVMain().run(args);
	}

	/**
	 * Überprüft die Parameter<br>
	 * 
	 * @param args
	 *            (default oder parameters)
	 * 
	 * @throws Exception
	 *             wenn keiner der Parameter eine Datei ist oder im falschen Dateiformat vorliegt
	 */
	private void run(String[] args) throws Exception {

		// Superklasse für Parameter (wie im ersten Projekt)
		AVParameterController parameter = new AVParameterController(args);

		if (parameter.isValid()) {
			fileToWatch = parameter.getFile();
			System.out.println("content of file: " + fileToWatch.getName());

			fileContent = new FileContent(fileToWatch);
			fileContent.displayFileContent();

		}

		if (parameter.isValid() && parameter.continuousChanges) {
			System.out.println("\nDie Datei '" + fileToWatch + "' wird auf Veränderungen überwacht\n");
			System.out.println("Beenden mit [STRG] + [C]");
			FileChangeNotifier fileChangeNotifier = new FileChangeNotifier(fileToWatch);
			fileChangeNotifier.watchDir(this);
		}

	}

	@Override
	public void fileChanged(WatchEvent<?> event) throws Exception {
		System.out.println("The file " + event.context() + " has been changed. This is the new content:");
		fileContent.displayFileContent();
	}

}

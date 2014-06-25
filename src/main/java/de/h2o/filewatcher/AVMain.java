package de.h2o.filewatcher;

import java.io.File;
import java.nio.file.WatchEvent;

public class AVMain implements FileChangeListener {

    FileContent fileContent; // TODO private
    File fileToWatch;

    /**
     * <file> .. Dateiinhalt ausgeben<br>
     * [-c] .. (countinously) Datei wird überwacht, bei jeder Änderung neu ausgeben<br>
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
            args = new String[]{ firstParameterDefault, secondParameterDefault };
        }

        new AVMain().run(args);
    }

    /**
     * Verifies the parameters. If the first parameter is a '.txt' or '.adoc' file <br>
     * the content of this file will be displayed.
     * 
     * If the second parameter is '-c' the file (1. parameter) will be monitored <br>
     * for changes. After every saved change of the file, the content of the file <br>
     * will be displayed again.
     * 
     * @param args
     *            (default or parameters)
     * 
     * @throws Exception
     *             if first parameter is no file, has wrong file format or does not exists.
     */
    private void run(String[] args) throws Exception {

        // Superklasse für Parameter (wie im ersten Projekt)
        AVParameterController parameter = new AVParameterController(args);

        if (parameter.isValid()) { // TODO restructure
            fileToWatch = parameter.getFileToWatch();
            System.out.println("content of file: " + fileToWatch.getName());

            fileContent = new FileContent(fileToWatch);
            fileContent.displayFileContent();

        }

        if (parameter.isValid() && parameter.isContinouosChangesActive()) {
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

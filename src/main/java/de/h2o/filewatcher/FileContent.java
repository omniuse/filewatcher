package de.h2o.filewatcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileContent {

	private File file;

	public FileContent(File file) {
		this.file = file;
	}

	/**
	 * 
	 * @return this method returns the whole content of the file
	 */
	private String getFileContent() throws Exception {

		// check if exists --> message
		if (!file.isFile()) {
			return "not a file!";
		}

		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		StringBuffer sb = new StringBuffer();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
			sb.append("\n");
		}
		br.close();
		return sb.toString();
	}

	public void displayFileContent() throws Exception {

		System.out.println("===============");
		System.out.println(getFileContent());
		System.out.println("===============");

	}
}

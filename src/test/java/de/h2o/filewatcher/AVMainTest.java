package de.h2o.filewatcher;

import org.junit.Ignore;
import org.junit.Test;

public class AVMainTest {
	/**
	 * this method shows some info about each test on the terminal
	 * 
	 * @param testName
	 */
	private void log(String testName) {
		String txt = "==> TEST '" + testName + "' started";
		System.out.println(txt);
		for (int i = 0; i < txt.length() + 1; i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	// long endtime = System.currentTimeMillis() + 1500; // TODO break test after 1500ms
	@Ignore
	@Test
	public void mainMethodParameterIsSuccessfulIfFileNameAndOtherThingsArePassed() throws Exception {
		log("ParameterIsSuccessfulIfFileNameAndOtherThingsArePassed");
		AVMain.main(new String[] { "tmp/foo.txt", "-c" });
		// while (endtime > System.currentTimeMillis()) {
		// System.out.println(endtime);
		// System.out.println(System.currentTimeMillis());
		// }

	}

	@Test(expected = RuntimeException.class)
	public void test1stParameterIsNoFile() throws Exception {
		log("test1stParameterIsNoFile");
		AVMain.main(new String[] { "-p", "tmp/foo.txt" });
	}

	@Test(expected = RuntimeException.class)
	public void test1stParameterFileDoesNotExists() throws Exception {
		log("test1stParameterFileDoesNotExists");
		AVMain.main(new String[] { "tmp/fooooo.txt", "-c" });
	}

	@Test(expected = RuntimeException.class)
	public void test1stParameterHasWrongFileFormat() throws Exception {
		log("test1stParameterHasWrongFileFormat");
		AVMain.main(new String[] { "tmp/foo.pdf", "-c" });
	}

	@Test(expected = RuntimeException.class)
	public void testNo2ndParameter() throws Exception {
		log("testNo2ndParameter");
		AVMain.main(new String[] { "tmp/foo.txt" });
	}

	@Test(expected = RuntimeException.class)
	public void test2ndParameterIsWrong() throws Exception {
		log("test2ndParameterIsWrong");
		AVMain.main(new String[] { "tmp/foo.txt", "-p" });
	}
}

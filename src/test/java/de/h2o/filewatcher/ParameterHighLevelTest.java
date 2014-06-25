package de.h2o.filewatcher;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ParameterHighLevelTest {

	@Test(expected = IllegalArgumentException.class)
	public void ifParameterIsNullExceptionIsThrown() {
		new AVParameterController(null);
	}

	@Test
	public void ifOneFileNameIsPassedEverythingIsFine() {
		AVParameterController parameterHighLevel = new AVParameterController(new String[] { "/tmp" });
		parameterHighLevel.isValid();

		assertEquals("/tmp", parameterHighLevel.getFileToWatch());
	}

}

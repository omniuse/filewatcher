package de.h2o.filewatcher;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ParameterHighLevelTest {

	@Test(expected = IllegalArgumentException.class)
	public void ifParameterIsNullExceptionIsThrown() {
		new ParameterHighLevel(null);
	}

	@Test
	public void ifOneFileNameIsPassedEverythingIsFine() {
		ParameterHighLevel parameterHighLevel = new ParameterHighLevel(new String[] { "/tmp" });
		parameterHighLevel.isValid();

		assertEquals("/tmp", parameterHighLevel.getFileToWatch());
	}

}

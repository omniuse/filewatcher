package de.h2o.filewatcher;

public class ParameterLowLevel {

	protected String[] args;

	public ParameterLowLevel(String[] args) {
		if (args == null) {
			throw new IllegalArgumentException("args is null");
		}

		this.args = args;
	}

}

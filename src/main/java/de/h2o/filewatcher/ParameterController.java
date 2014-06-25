package de.h2o.filewatcher;

// ************************ LOW LEVEL METHODS
public class ParameterController {

    protected String[] args;

    public ParameterController(String[] args) {
        if (args == null) {
            throw new IllegalArgumentException("args is null");
        }

        this.args = args;
    }

}

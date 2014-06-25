package de.h2o.parameter;

import org.junit.Test;

public class MainTest {
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

    @Test
    public void mainMethodParameterIsSuccessfulIfFileNameAndOtherThingsArePassed() throws Exception {
        log("ParameterIsSuccessfulIfFileNameAndOtherThingsArePassed");
        MainTest.main(new String[]{ "tmp/x.foo", "-n", "-p", "8" });
    }

    @Test
    public void mainMethodParameterIsSuccessfulIfFileNameAndNoNumberIsPassed() throws Exception {
        log("ParameterIsSuccessfulIfFileNameAndNoNumberIsPassed");
        Main.main(new String[]{ "tmp/x.foo", "-n", "-p" });
    }

    @Test
    public void mainMethodParameterIsSuccessfulIfFileNameAndNoNIsPassed() throws Exception {
        log("ParameterIsSuccessfulIfFileNameAndNoNIsPassed");
        Main.main(new String[]{ "tmp/x.foo", "5", "-p" });
    }
}

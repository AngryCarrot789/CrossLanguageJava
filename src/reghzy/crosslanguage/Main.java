package reghzy.crosslanguage;

import reghzy.crosslanguage.function.FunctionProcessor;
import reghzy.crosslanguage.function.ParameterCreator;
import reghzy.crosslanguage.reflection.MethodHelper;
import reghzy.crosslanguage.serial.SerialFunctionProcessor;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
    }

    public Main() {
        FunctionProcessor processor = new SerialFunctionProcessor("COM21", new ParameterCreator(), true);
        processor.getRegister().registerFunction1("writeConsole", this, this.getClass());
        processor.getDispatcher().dispatchFunction("WriteConsole", "hi");

        while(true) {

        }
    }

    public void writeConsole(Object text) {
        System.out.println(text.toString());
    }
}

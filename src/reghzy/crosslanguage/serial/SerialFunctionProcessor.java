package reghzy.crosslanguage.serial;

import reghzy.crosslanguage.function.FuncNameParamsPair;
import reghzy.crosslanguage.function.FunctionProcessor;
import reghzy.crosslanguage.function.FunctionRegister;
import reghzy.crosslanguage.function.ParameterCreator;
import reghzy.crosslanguage.function.functions.IFunction;

public class SerialFunctionProcessor extends FunctionProcessor {
    private final SerialTransceiver transceiver;

    public SerialFunctionProcessor(String port, ParameterCreator parameterCreator, boolean openPort) {
        super(parameterCreator);
        this.transceiver = new SerialTransceiver(port, new DataReceiver());
        this.dispatcher = new SerialFunctionDispatcher(this.transceiver, this.parameters);
        this.register = new FunctionRegister();

        if (openPort) {
            this.transceiver.connect();
        }
    }

    protected void onSerialisedFunctionReceived(String name, String parameters) {
        IFunction function = register.getFunction(name);
        if (function == null) {
            System.err.println("Received an unknown function: " + name);
        }
        else {
            this.parameters.deserialiseAndAppend(parameterStack, parameters);
            try {
                invokeFunction(function);
            }
            catch (ClassCastException e) {
                System.err.println("Failed to cast IFunction to its actual class type. this is a fatal user error, " +
                                   "probably caused by the wrong number of parameters in the function or when being received from the serial port");
                System.err.println("    Parameters count: " + parameterStack.size() + '\n' +
                                   "    Expected function class: Function" + parameterStack.size() + '\n' +
                                   "    Error function class: " + function.toString());
                e.printStackTrace();
            }
            parameterStack.clear();
        }
    }

    private class DataReceiver implements IMessageListener {
        @Override
        public void onLineReceived(String content) {
            FuncNameParamsPair pair = parameters.splitFuncNameParams(content);
            if (pair == null) {
                System.err.println("Failed to parse function invocation string");
            }
            else if (pair.functionName == null) {
                System.err.println("Failed to receive a function invocation: Received function name was null");
            }
            else if (pair.functionParams == null) {
                System.err.println("Failed to receive a function invocation: Serialised parameter was null");
            }
            else {
                onSerialisedFunctionReceived(pair.functionName, pair.functionParams);
            }
        }
    }
}

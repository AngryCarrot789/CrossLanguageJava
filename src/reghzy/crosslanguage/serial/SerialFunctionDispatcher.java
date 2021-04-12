package reghzy.crosslanguage.serial;

import reghzy.crosslanguage.function.IFunctionDispatcher;
import reghzy.crosslanguage.function.ParameterCreator;

public class SerialFunctionDispatcher implements IFunctionDispatcher {
    private final ParameterCreator parameterCreator;
    private final SerialTransceiver transceiver;

    public SerialFunctionDispatcher(SerialTransceiver transceiver, ParameterCreator parameterCreator) {
        this.transceiver = transceiver;
        this.parameterCreator = parameterCreator;
    }

    @Override
    public void dispatchFunction(String name) {
        this.transceiver.transmitter.sendMessageLine(this.parameterCreator.joinFunction(name, this.parameterCreator.serialiseParameters()));
    }

    @Override
    public <T1> void dispatchFunction(String name, T1 a) {
        this.transceiver.transmitter.sendMessageLine(this.parameterCreator.joinFunction(name, this.parameterCreator.serialiseParameters(a)));
    }

    @Override
    public <T1, T2> void dispatchFunction(String name, T1 a, T2 b) {
        this.transceiver.transmitter.sendMessageLine(this.parameterCreator.joinFunction(name, this.parameterCreator.serialiseParameters(a, b)));
    }

    @Override
    public <T1, T2, T3> void dispatchFunction(String name, T1 a, T2 b, T3 c) {
        this.transceiver.transmitter.sendMessageLine(this.parameterCreator.joinFunction(name, this.parameterCreator.serialiseParameters(a, b, c)));
    }

    @Override
    public <T1, T2, T3, T4> void dispatchFunction(String name, T1 a, T2 b, T3 c, T4 d) {
        this.transceiver.transmitter.sendMessageLine(this.parameterCreator.joinFunction(name, this.parameterCreator.serialiseParameters(a, b, c, d)));
    }

    @Override
    public <T1, T2, T3, T4, T5> void dispatchFunction(String name, T1 a, T2 b, T3 c, T4 d, T5 e) {
        this.transceiver.transmitter.sendMessageLine(this.parameterCreator.joinFunction(name, this.parameterCreator.serialiseParameters(a, b, c, d, e)));
    }

    @Override
    public <T1, T2, T3, T4, T5, T6> void dispatchFunction(String name, T1 a, T2 b, T3 c, T4 d, T5 e, T6 f) {
        this.transceiver.transmitter.sendMessageLine(this.parameterCreator.joinFunction(name, this.parameterCreator.serialiseParameters(a, b, c, d, e, f)));
    }
}

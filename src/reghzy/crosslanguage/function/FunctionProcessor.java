package reghzy.crosslanguage.function;

import reghzy.crosslanguage.function.functions.*;

import java.util.ArrayList;

public abstract class FunctionProcessor {
    protected IFunctionDispatcher dispatcher;
    protected FunctionRegister register;
    protected ParameterCreator parameters;
    protected final ArrayList<Object> parameterStack;

    public FunctionProcessor(ParameterCreator parameterCreator) {
        this.parameters = parameterCreator;
        this.parameterStack = new ArrayList<Object>(6);
    }

    protected void invokeFunction(IFunction function) {
        switch (parameterStack.size()) {
            case 0: ((Function0) function).invoke(); break;
            case 1: ((Function1) function).invoke(parameterStack.get(0)); break;
            case 2: ((Function2) function).invoke(parameterStack.get(0), parameterStack.get(1)); break;
            case 3: ((Function3) function).invoke(parameterStack.get(0), parameterStack.get(1), parameterStack.get(2)); break;
            case 4: ((Function4) function).invoke(parameterStack.get(0), parameterStack.get(1), parameterStack.get(2), parameterStack.get(3)); break;
            case 5: ((Function5) function).invoke(parameterStack.get(0), parameterStack.get(1), parameterStack.get(2), parameterStack.get(3), parameterStack.get(4)); break;
            case 6: ((Function6) function).invoke(parameterStack.get(0), parameterStack.get(1), parameterStack.get(2), parameterStack.get(3), parameterStack.get(4), parameterStack.get(5)); break;
        }
    }

    public IFunctionDispatcher getDispatcher() {
        return dispatcher;
    }

    public FunctionRegister getRegister() {
        return register;
    }
}

package reghzy.crosslanguage.function;

import reghzy.crosslanguage.function.functions.*;
import reghzy.crosslanguage.reflection.MethodHelper;

import java.util.HashMap;

public class FunctionRegister {
    private final HashMap<String, IFunction> functionMap;

    public FunctionRegister() {
        this.functionMap = new HashMap<String, IFunction>(16);
    }

    public void registerFunction0(String name, Object instance, Class<?> owner) {
        this.functionMap.put(name, new Function0(instance, MethodHelper.getMethodParams0(owner, name)));
    }

    public void registerFunction1(String name, Object instance, Class<?> owner) {
        this.functionMap.put(name, new Function1(instance, MethodHelper.getMethodParams1(owner, name)));
    }

    public void registerFunction2(String name, Object instance, Class<?> owner) {
        this.functionMap.put(name, new Function2(instance, MethodHelper.getMethodParams2(owner, name)));
    }

    public void registerFunction3(String name, Object instance, Class<?> owner) {
        this.functionMap.put(name, new Function3(instance, MethodHelper.getMethodParams3(owner, name)));
    }

    public void registerFunction4(String name, Object instance, Class<?> owner) {
        this.functionMap.put(name, new Function4(instance, MethodHelper.getMethodParams4(owner, name)));
    }

    public void registerFunction5(String name, Object instance, Class<?> owner) {
        this.functionMap.put(name, new Function5(instance, MethodHelper.getMethodParams5(owner, name)));
    }

    public void registerFunction6(String name, Object instance, Class<?> owner) {
        this.functionMap.put(name, new Function6(instance, MethodHelper.getMethodParams6(owner, name)));
    }

    public void unregisterFunction(String name) {
        this.functionMap.remove(name);
    }

    public IFunction getFunction(String name) {
        return this.functionMap.get(name);
    }
}

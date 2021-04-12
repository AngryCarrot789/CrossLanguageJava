package reghzy.crosslanguage.function.functions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Function1 implements IFunction {
    private final Method method;
    private final Object instance;

    public Function1(Object instance, Method method) {
        this.instance = instance;
        this.method = method;
    }

    public void invoke(Object p1) {
        try {
            this.method.invoke(instance, p1);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

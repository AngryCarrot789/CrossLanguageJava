package reghzy.crosslanguage.function.functions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Function5 implements IFunction {
    private final Method method;
    private final Object instance;

    public Function5(Object instance, Method method) {
        this.instance = instance;
        this.method = method;
    }

    public void invoke(Object p1, Object p2, Object p3, Object p4, Object p5) {
        try {
            this.method.invoke(instance, p1, p2, p3, p4, p5);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
package reghzy.crosslanguage.function.functions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Function6 implements IFunction {
    private final Method method;
    private final Object instance;

    public Function6(Object instance, Method method) {
        this.instance = instance;
        this.method = method;
    }

    public void invoke(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        try {
            this.method.invoke(instance, p1, p2, p3, p4, p5, p6);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

package reghzy.crosslanguage.function.functions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Function0 implements IFunction {
    private final Method method;
    private final Object instance;

    public Function0(Object instance, Method method) {
        this.instance = instance;
        this.method = method;
    }

    public void invoke() {
        try {
            this.method.invoke(instance, (Object) null);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

package reghzy.crosslanguage.reflection;

import java.lang.reflect.Method;

public class MethodHelper {
    public static Method getMethodParams0(Class<?> clazz, String name) {
        try {
            return clazz.getMethod(name, (Class<?>) null);
        }
        catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Method getMethodParams1(Class<?> clazz, String name) {
        try {
            return clazz.getMethod(name, Object.class);
        }
        catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Method getMethodParams2(Class<?> clazz, String name) {
        try {
            return clazz.getMethod(name, Object.class, Object.class);
        }
        catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Method getMethodParams3(Class<?> clazz, String name) {
        try {
            return clazz.getMethod(name, Object.class, Object.class, Object.class);
        }
        catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Method getMethodParams4(Class<?> clazz, String name) {
        try {
            return clazz.getMethod(name, Object.class, Object.class, Object.class, Object.class);
        }
        catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Method getMethodParams5(Class<?> clazz, String name) {
        try {
            return clazz.getMethod(name, Object.class, Object.class, Object.class, Object.class, Object.class);
        }
        catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Method getMethodParams6(Class<?> clazz, String name) {
        try {
            return clazz.getMethod(name, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class);
        }
        catch (NoSuchMethodException e) {
            return null;
        }
    }
}

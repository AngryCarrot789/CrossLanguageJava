package reghzy.crosslanguage.function;

public interface IFunctionDispatcher {
    void dispatchFunction(String name);
    <T1> void dispatchFunction(String name, T1 a);
    <T1, T2> void dispatchFunction(String name, T1 a, T2 b);
    <T1, T2, T3> void dispatchFunction(String name, T1 a, T2 b, T3 c);
    <T1, T2, T3, T4> void dispatchFunction(String name, T1 a, T2 b, T3 c, T4 d);
    <T1, T2, T3, T4, T5> void dispatchFunction(String name, T1 a, T2 b, T3 c, T4 d, T5 e);
    <T1, T2, T3, T4, T5, T6> void dispatchFunction(String name, T1 a, T2 b, T3 c, T4 d, T5 e, T6 f);
}

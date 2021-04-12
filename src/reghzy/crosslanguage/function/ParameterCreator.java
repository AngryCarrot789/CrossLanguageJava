package reghzy.crosslanguage.function;

import java.util.ArrayList;

public class ParameterCreator {
    public char nameParameterSplitter = ':';
    public char parameterSplitter = '|';
    public char stringEncapsulator = '\'';
    public char stringEncapsulatorCancel = '\\';

    private final StringBuilder parameterBuilder;

    public ParameterCreator() {
        parameterBuilder = new StringBuilder(128);
    }

    public void deserialiseAndAppend(ArrayList<Object> stack, String serialised) {
        if (serialised.length() < 2)
            return;

        for (int i = 0, length = serialised.length(); i < length; i++) {
            char c = serialised.charAt(i);
            if (c == 'i') {
                i = extractParameter(parameterBuilder, serialised, i);
                stack.add(Integer.parseInt(parameterBuilder.toString()));
                parameterBuilder.setLength(0);
            }
            else if (c == 'd') {
                i = extractParameter(parameterBuilder, serialised, i);
                stack.add(Double.parseDouble(parameterBuilder.toString()));
                parameterBuilder.setLength(0);
            }
            else if (c == 'b') {
                i = extractParameter(parameterBuilder, serialised, i);
                stack.add(Boolean.parseBoolean(parameterBuilder.toString()));
                parameterBuilder.setLength(0);
            }
            else if (c == 's') {
                c = serialised.charAt(++i);
                if (c == stringEncapsulator) {
                    StringEncapsulatorIntPair pair = encapsulatorEnd(serialised, ++i);
                    if (pair.encapsulatorIndex == -1)
                        pair.encapsulatorIndex = pair.lastEncapsulatorIndex;

                    while (i < pair.encapsulatorIndex) {
                        parameterBuilder.append(serialised.charAt(i++));
                    }
                    stack.add(parameterBuilder.toString());
                    parameterBuilder.setLength(0);
                    i++;
                }
            }
        }
    }

    private int extractParameter(StringBuilder parameterBuilder, String serialised, int startIndex) {
        int indexLength = serialised.length() - 1;
        char c = serialised.charAt(++startIndex);
        while(c != parameterSplitter) {
            parameterBuilder.append(c);
            if (startIndex == indexLength)
                break;
            c = serialised.charAt(++startIndex);
        }
        return startIndex;
    }

    private StringEncapsulatorIntPair encapsulatorEnd(String serialised, int i) {
        int lastEncapsulatorIndex = -1;
        for (int j = i, len = serialised.length(), indexLen = len - 1; j < len; j++) {
            if (serialised.charAt(j) == stringEncapsulator) {
                lastEncapsulatorIndex = j;
                if (j == indexLen)
                    return new StringEncapsulatorIntPair(lastEncapsulatorIndex, j);

                if (serialised.charAt(j + 1) == parameterSplitter) {
                    if (serialised.charAt(j - 1) != stringEncapsulatorCancel) {
                        return new StringEncapsulatorIntPair(lastEncapsulatorIndex, j);
                    }
                }
            }
        }
        // something terrible has happened...
        return new StringEncapsulatorIntPair(lastEncapsulatorIndex, -1);
    }

    public FuncNameParamsPair splitFuncNameParams(String input) {
        int splitIndex = input.indexOf(nameParameterSplitter);
        if (splitIndex == -1)
            return null;

        String functionName = input.substring(0, splitIndex);
        String parameters = input.substring(splitIndex + 1);
        return new FuncNameParamsPair(functionName, parameters);
    }

    public String joinFunction(String name, String serialisedParams) {
        return name + nameParameterSplitter + serialisedParams;
    }

    public String serialiseParameters() {
        return "!";
    }

    public <T1> String serialiseParameters(T1 p1) {
        return serialiseType(p1);
    }

    public <T1, T2> String serialiseParameters(T1 p1, T2 p2) {
        return new StringBuilder().
                append(serialiseType(p1)).append(parameterSplitter).
                append(serialiseType(p2)).toString();
    }

    public <T1, T2, T3> String serialiseParameters(T1 p1, T2 p2, T3 p3) {
        return new StringBuilder().
                append(serialiseType(p1)).append(parameterSplitter).
                append(serialiseType(p2)).append(parameterSplitter).
                append(serialiseType(p3)).toString();
    }

    public <T1, T2, T3, T4> String serialiseParameters(T1 p1, T2 p2, T3 p3, T4 p4) {
        return new StringBuilder().
                append(serialiseType(p1)).append(parameterSplitter).
                append(serialiseType(p2)).append(parameterSplitter).
                append(serialiseType(p3)).append(parameterSplitter).
                append(serialiseType(p4)).toString();
    }

    public <T1, T2, T3, T4, T5> String serialiseParameters(T1 p1, T2 p2, T3 p3, T4 p4, T5 p5) {
        return new StringBuilder().
                append(serialiseType(p1)).append(parameterSplitter).
                append(serialiseType(p2)).append(parameterSplitter).
                append(serialiseType(p3)).append(parameterSplitter).
                append(serialiseType(p4)).append(parameterSplitter).
                append(serialiseType(p5)).toString();
    }

    public <T1, T2, T3, T4, T5, T6> String serialiseParameters(T1 p1, T2 p2, T3 p3, T4 p4, T5 p5, T6 p6) {
        return new StringBuilder().
                append(serialiseType(p1)).append(parameterSplitter).
                append(serialiseType(p2)).append(parameterSplitter).
                append(serialiseType(p3)).append(parameterSplitter).
                append(serialiseType(p4)).append(parameterSplitter).
                append(serialiseType(p5)).append(parameterSplitter).
                append(serialiseType(p6)).toString();
    }


    private static <T> String serialiseType(T t) {
        if (t.getClass().equals(Integer.class))
            return serialiseInteger(t.toString());
        if (t.getClass().equals(Double.class))
            return serialiseDouble(t.toString());
        if (t.getClass().equals(Boolean.class))
            return serialiseBoolean(t.toString());
        if (t.getClass().equals(String.class))
            return serialiseString(t.toString());

        throw new UnsupportedOperationException("That type is not supported for serialisation");
    }

    private static String serialiseInteger(String value) {
        return 'i' + value;
    }

    private static String serialiseDouble(String value) {
        return 'd' + value;
    }

    private static String serialiseBoolean(String value) {
        return 'b' + value;
    }

    private static String serialiseString(String value) {
        return "s'" + value + "'";
    }

    private static class StringEncapsulatorIntPair {
        public int lastEncapsulatorIndex;
        public int encapsulatorIndex;

        public StringEncapsulatorIntPair(int lastEncapsulatorIndex, int encapsulatorIndex) {
            this.lastEncapsulatorIndex = lastEncapsulatorIndex;
            this.encapsulatorIndex = encapsulatorIndex;
        }
    }
}

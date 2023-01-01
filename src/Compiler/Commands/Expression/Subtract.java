package Compiler.Commands.Expression;

// this class returns the subtracted value of two numbers
public class Subtract extends Expression {

    public static Number subtract(Number V1, Number V2) {
        setResult((check(V1) ? V1.intValue() : V1.floatValue()) - (check(V2) ? V2.intValue() : V2.floatValue()));
        return getResult();
    }

}

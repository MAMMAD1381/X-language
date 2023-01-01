package Compiler.Commands.Expression;

// this class returns the multiplied value of two numbers
public class Multiple extends Expression {

    public static Number multiple(Number V1, Number V2) {
        setResult((check(V1) ? V1.intValue() : V1.floatValue()) * (check(V2) ? V2.intValue() : V2.floatValue()));
        return getResult();
    }
}

package Compiler.Commands.Expression;

// this class returns the summed value of two numbers
public class Sum extends Expression {

    public static Number sum(Number V1, Number V2) {
        setResult((check(V1) ? V1.intValue() : V1.floatValue()) + (check(V2) ? V2.intValue() : V2.floatValue()));
        return getResult();
    }

}

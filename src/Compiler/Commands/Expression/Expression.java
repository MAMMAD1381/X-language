package Compiler.Commands.Expression;

import Compiler.Variables.MyFloat;
import Compiler.Variables.MyInteger;

// this class includes the math expression in this program
public abstract class Expression {

    private static Number result;

    // this function checks that our number is an Integer or Float or MyInteger or MyFloat
    public static boolean check(Number value) {
        if (value instanceof Integer || value instanceof MyInteger) {
            return true;
        } else if (value instanceof Float || value instanceof MyFloat) {
            return false;
        }
        return false;
    }

    public static Number getResult() {
        return result;
    }

    public static void setResult(Number result) {
        Expression.result = result;
    }

}

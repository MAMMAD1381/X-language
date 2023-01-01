package Compiler.Commands;

import Compiler.Commands.Expression.*;
import Compiler.Variables.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// if there is an assignment command, this class will be called
public class Assignment {
    private static ArrayList<MyInteger> integers;
    private static ArrayList<MyFloat> floats;

    // this function does the main job
    public static Number assignment(Scanner scanner, ArrayList<MyInteger> integers,
                                    ArrayList<MyFloat> floats, Variable number) {
        Assignment.integers = integers;
        Assignment.floats = floats;
        Number returnValue = 0;

        if (scanner.hasNext("={1}")) {
            scanner.next();
            String value = scanner.next();
            String pattern = "[\\+\\-\\*\\/ ]+";// regex of math expressions

            if (scanner.hasNext(pattern)) {
                returnValue = expression(scanner, value, number);
            } else {
                if (value.matches(Pattern.INT_PATTERN) || value.matches(Pattern.FLOAT_PATTERN)) {
                    returnValue = literal(value, number);
                } else {
                    returnValue = variable(value, number);
                }
            }
        }
        return returnValue;
    }

    // if the right side of assignment is a literal, this function will be called
    public static Number literal(String value, Variable number) {
        if (value.matches(Pattern.INT_PATTERN)) {
            if (number instanceof MyInteger) {
                ((MyInteger) number).setValue(Integer.parseInt(value));
                return ((MyInteger) number).getValue();
            } else {
                throw new InputMismatchException("Required type: float ; Provided: int");
            }
        } else if (value.matches(Pattern.FLOAT_PATTERN)) {
            if (number instanceof MyFloat) {
                ((MyFloat) number).setValue(Float.parseFloat(value));
                ((MyFloat) number).getValue();
            } else {

                throw new InputMismatchException("Required type: int ; Provided: float");
            }
        }
        return 0;
    }

    // if the right side of assignment is a local variable type, this function will be called
    public static Number variable(String value, Variable number) {
        for (MyInteger myInteger : integers) {
            if (myInteger.getName().equals(value)) {
                if (number instanceof MyInteger) {
                    ((MyInteger) number).setValue(myInteger.getValue());
                    return ((MyInteger) number).getValue();
                } else {
                    throw new InputMismatchException("Required type: float ; Provided: int");
                }
            }
        }

        for (MyFloat myFloat : floats) {
            if (myFloat.getName().equals(value)) {
                if (number instanceof MyFloat) {
                    ((MyFloat) number).setValue(myFloat.getValue());
                    return ((MyFloat) number).getValue();
                } else {
                    throw new InputMismatchException("Required type: int ; Provided: float");
                }
            }
        }
        return 0;
    }

    // if the right side of assignment is a math expression, this function will be called
    public static Number expression(Scanner scanner, String value1, Variable number) {
        String symbol = scanner.next();
        String value2 = scanner.next();
        Number V1 = searchInVariables(value1);
        Number V2 = searchInVariables(value2);

        switch (symbol) {
            case "+":
                if (number instanceof MyInteger) {
                    ((MyInteger) number).setValue(Sum.sum(V1, V2).intValue());
                    return ((MyInteger) number).getValue();
                } else {
                    ((MyFloat) number).setValue(Sum.sum(V1, V2).floatValue());
                    return ((MyFloat) number).getValue();
                }

            case "-":
                if (number instanceof MyInteger) {
                    ((MyInteger) number).setValue(Subtract.subtract(V1, V2).intValue());
                    return ((MyInteger) number).getValue();
                } else {
                    ((MyFloat) number).setValue(Subtract.subtract(V1, V2).floatValue());
                    return ((MyFloat) number).getValue();
                }
            case "*":
                if (number instanceof MyInteger) {
                    ((MyInteger) number).setValue(Multiple.multiple(V1, V2).intValue());
                    return ((MyInteger) number).getValue();
                } else {
                    ((MyFloat) number).setValue(Multiple.multiple(V1, V2).floatValue());
                    return ((MyFloat) number).getValue();
                }
            case "/":
                if (number instanceof MyInteger) {
                    ((MyInteger) number).setValue(Divide.divide(V1, V2).intValue());
                    return ((MyInteger) number).getValue();
                } else {
                    ((MyFloat) number).setValue(Divide.divide(V1, V2).floatValue());
                    return ((MyFloat) number).getValue();
                }
        }
        return 0;
    }

    // returns the amount of input variable name
    public static Number searchInVariables(String name) {

        if (name.matches(Pattern.INT_PATTERN)) {
            return Integer.parseInt(name);
        } else if (name.matches(Pattern.FLOAT_PATTERN)) {
            return Float.parseFloat(name);
        }

        for (MyInteger integer : integers) {
            if (integer.getName().equals(name)) {
                return integer;
            }
        }
        for (MyFloat myFloat : floats) {
            if (myFloat.getName().equals(name)) {
                return myFloat;
            }
        }
        return null;
    }

}


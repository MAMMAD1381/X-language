package Compiler.Commands;

import Compiler.Variables.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Print {
    
    public static int print(Scanner scanner, ArrayList<MyInteger> integers, ArrayList<MyFloat> floats){
        int returnValue = 0;
        String value = scanner.next();

        for (MyFloat myFloat : floats) {
            if (myFloat.getName().equals(value)) {
                returnValue = printVariable(myFloat);
                break;
            }
        }

        for (MyInteger myInteger : integers) {
            if (myInteger.getName().equals(value)) {
                returnValue = printVariable(myInteger);
                break;
            }
        }

        if (value.matches(Pattern.INT_PATTERN)) {
            returnValue = printInt(Integer.parseInt(value));

        } else if (value.matches(Pattern.FLOAT_PATTERN)) {
            returnValue = printFloat(Float.parseFloat(value));
        }
        
        return returnValue;
    }

    private static int printVariable(Variable variable) {
        if (variable instanceof MyInteger) {
            System.out.println(((MyInteger) variable).getValue());
            return String.valueOf(((MyInteger) variable).getValue()).length();

        } else {
            System.out.println(((MyFloat) variable).getValue());
            return String.valueOf(((MyFloat) variable).getValue()).length();
        }
    }

    private static int printInt(int i) {
        System.out.println(i);
        return String.valueOf(i).length();
    }

    private static int printFloat(float f) {
        System.out.println(f);
        return String.valueOf(f).length();
    }

}

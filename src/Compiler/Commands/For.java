package Compiler.Commands;

import Compiler.Variables.MyFloat;
import Compiler.Variables.MyInteger;
import Compiler.Variables.Variable;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class For {

    // if a for loop is needed this function will be called
    public static int forLoop(Scanner scanner, ArrayList<MyInteger> integers, ArrayList<MyFloat> floats) {
        int count;
        if (!scanner.hasNextInt()) {
            throw new InputMismatchException();
        } else {
            count = scanner.nextInt();
        }
        ArrayList<String> lines = new ArrayList<>();

        // we will save the commands of the for loop in an array
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("end for")) {
                break;
            }
            lines.add(line);
        }

        // we will do the commands of the for loop here after we scanned the commands on the previous loop
        for (int i = 0; i < count; i++) {
            for (int j = 1; j < lines.size(); j++) {
                Scanner scanner2 = new Scanner(lines.get(j));
                String line = scanner2.next();
                switch (line) {
                    case "print":
                        Print.print(scanner2, integers, floats);
                        break;
                    default:
                        if (searchInVariables(line, integers, floats) != null) {
                            Assignment.assignment(scanner2, integers, floats, searchInVariables(line, integers, floats));
                        } else {
                            throw new IllegalArgumentException("Unknown Data");
                        }
                }
            }
        }
        return count;
    }

    // returns the amount of input variable name
    public static Variable searchInVariables(String name, ArrayList<MyInteger> integers,
                                             ArrayList<MyFloat> floats) {
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


import Compiler.Commands.Assignment;
import Compiler.Commands.For;
import Compiler.Commands.Print;
import Compiler.Variables.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// reads from the file
public class FileReader {

    private Scanner scanner;

    private ArrayList<MyInteger> integers = new ArrayList<>();
    private ArrayList<MyFloat> floats = new ArrayList<>();

    public FileReader(File file) throws FileNotFoundException {
        read(file);
    }

    public void read(File file) throws FileNotFoundException {
        java.io.FileReader reader = new java.io.FileReader(file);
        scanner = new Scanner(reader);

        // first part of the program(definition of variables)
        while (scanner.hasNext()) {
            String line = scanner.next();

            if (line.equals("%%")) {
                break;
            }

            switch (line) {
                case "int":
                    newInt(scanner);
                    break;
                case "float":
                    newFloat(scanner);
                    break;
                default:
                    throw new IllegalArgumentException("Illegal Variable Type");
            }
        }

        // second part of the program(commands of program)
        while (scanner.hasNext()) {

            String line = scanner.next();

            switch (line) {
                case "print":
                    Print.print(scanner, integers, floats);
                    break;
                case "for":
                    For.forLoop(scanner, integers, floats);
                    break;
                default:
                    if (searchInVariables(line) != null) {
                        Assignment.assignment(scanner, integers, floats, searchInVariables(line));
                    } else {
                        throw new IllegalArgumentException("Unknown Data");
                    }
            }
        }
    }

    // if there is no integer with defined name, we will add it to our "integers"
    public void newInt(Scanner scanner) {
        String intName = scanner.next();

        if (searchInVariables(intName) != null) {
            throw new IllegalArgumentException("This variable is already defined");
        } else {
            MyInteger integer = null;

            try {
                integer = new MyInteger(intName);
            } catch (IllegalVariableNameException e) {
                e.printStackTrace();
                System.exit(1);
            }

            integers.add(integer);

            if (scanner.hasNext("={1}")) {
                scanner.next();
                integers.get(integers.size() - 1).setValue(scanner.nextInt());
            }
            if (scanner.hasNext(",")) {
                scanner.next();
                if (scanner.hasNext()) {
                    newInt(scanner);
                }
            }
        }

    }

    // if there is no float with defined name, we will add it to our "floats"
    public void newFloat(Scanner scanner) {
        String floatName = scanner.next();

        if (searchInVariables(floatName) != null) {
            throw new IllegalArgumentException("This variable is already defined");
        } else {
            MyFloat Float = null;

            try {
                Float = new MyFloat(floatName);
            } catch (IllegalVariableNameException e) {
                e.printStackTrace();
                System.exit(1);
            }

            floats.add(Float);

            if (scanner.hasNext("={1}")) {
                scanner.next();
                floats.get(floats.size() - 1).setValue(scanner.nextFloat());
            }

            if (scanner.hasNext(",")) {
                scanner.next();
                if (scanner.hasNext()) {
                    newFloat(scanner);
                }
            }
        }

    }

    // returns the amount of input variable name
    public Variable searchInVariables(String name) {
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
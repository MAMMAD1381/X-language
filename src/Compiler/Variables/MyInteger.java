package Compiler.Variables;

// local variable MyInteger
public class MyInteger extends Variable {

    private int value;

    public MyInteger(String name) throws IllegalVariableNameException {
        super(name);
    }

    public MyInteger(String name, int value) throws IllegalVariableNameException {
        super(name);
        setValue(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // these functions are needed to be overridden because this class extends "Variable" class
    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return 0;
    }

    @Override
    public float floatValue() {
        return 0;
    }

    @Override
    public double doubleValue() {
        return 0;
    }
}

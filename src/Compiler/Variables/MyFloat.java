package Compiler.Variables;

// local variable MyFloat
public class MyFloat extends Variable {

    private float value;

    public MyFloat(String name) throws IllegalVariableNameException {
        super(name);
    }

    public MyFloat(String name, float value) throws IllegalVariableNameException {
        super(name);
        setValue(value);
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    // these functions are needed to be overridden because this class extends "Variable" class
    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public long longValue() {
        return 0;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return 0;
    }

}

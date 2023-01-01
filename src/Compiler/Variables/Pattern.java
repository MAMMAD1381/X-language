package Compiler.Variables;

// variable patterns
public abstract class Pattern {
    public static final String INT_PATTERN = "[-]?[0-9]+";
    public static final String FLOAT_PATTERN = "[-]?\\d+\\.\\d+";
    public static final String NAME_PATTERN = "^[_$a-z][\\w$]*$";
}

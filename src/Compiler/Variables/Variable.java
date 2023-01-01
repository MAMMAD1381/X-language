package Compiler.Variables;

public abstract class Variable extends Number {

    private String name;

    public Variable(String name) throws IllegalVariableNameException {
        setName(name);
    }

    public String getName() {
        return name;
    }

    // before setting the name of a variable, we check that if it matches to Java naming rules
    public void setName(String name) throws IllegalVariableNameException {

        if (name.charAt(0) == '_' && name.length() <= 1) {
            throw new IllegalVariableNameException("Illegal Variable Name");
        } else if (name.matches(Pattern.NAME_PATTERN)) {
            this.name = name;
        } else {
            throw new IllegalVariableNameException("Illegal Variable Name");
        }
    }
}

package demo;

import java.util.Objects;

public class Value {
    private static Value ourInstance = new Value();

    public static Value getInstance() {
        return ourInstance;
    }

    private String value = "";


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if(!Objects.equals(this.value, value))
        this.value = value;
    }

    private Value() {
    }
}

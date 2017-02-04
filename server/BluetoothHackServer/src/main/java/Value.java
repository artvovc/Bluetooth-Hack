public class Value {
    private static Value ourInstance = new Value();

    public static Value getInstance() {
        return ourInstance;
    }

    String value = "";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private Value() {
    }
}

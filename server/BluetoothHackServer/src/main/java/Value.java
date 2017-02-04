public class Value {
    private static Value ourInstance = new Value();

    public static Value getInstance() {
        return ourInstance;
    }

    int value = 0;

    public int getValue() {
        return value;
    }

    public void setValue() {
        this.value++;
        if(this.value == 2) this.value=0;
    }

    private Value() {
    }
}

import javax.swing.*;

/**
 * Created by Artemie on 04.02.2017.
 */
public class Frame {
    private static Frame ourInstance = new Frame();

    public static Frame getInstance() {
        return ourInstance;
    }

    JFrame f = null;

    public JFrame getF() {
        return f;
    }

    public void setF(JFrame f) {
        this.f = f;
    }

    private Frame() {
    }
}

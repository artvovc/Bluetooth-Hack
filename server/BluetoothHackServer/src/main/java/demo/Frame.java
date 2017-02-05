package demo;

import javax.swing.*;

public class Frame {
    private static Frame ourInstance = new Frame();

    public static Frame getInstance() {
        return ourInstance;
    }

    JFrame frame = null;

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    private Frame() {
    }
}
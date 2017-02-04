import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.ds.ipcam.IpCamAuth;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class Client {

    static {
        Webcam.setDriver(new IpCamDriver());
    }

    public static void main(String[] args) throws MalformedURLException {

//        System.setProperty("com.sun.net.ssl.checkRevocation","false");
        IpCamDeviceRegistry.register("First", "", IpCamMode.PUSH);
//        IpCamDeviceRegistry.register("Second", "https://10.244.52.172:8080/watch/upqve07qcscz094j", IpCamMode.PUSH);

//        Webcam webcam = Webcam.getWebcams().get(0);
//        Webcam webcam1 = Webcam.getDefault();
//        webcam1.open();

//        WebcamPanel panel = new WebcamPanel(Webcam.getDefault());



//        panel.setFPSLimit(1);




        JTextField jTextField = new JTextField("id");
        jTextField.setSize(new Dimension(200,200));
        jTextField.setMaximumSize(new Dimension(200,200));
        jTextField.setPreferredSize(new Dimension(200,200));
        jTextField.setMinimumSize(new Dimension(200,200));


        JTextField jTextField1 = new JTextField();
        jTextField1.setSize(new Dimension(200,200));
        jTextField1.setMaximumSize(new Dimension(200,200));
        jTextField1.setPreferredSize(new Dimension(200,200));
        jTextField1.setMinimumSize(new Dimension(200,200));

        JButton jButton = new JButton("Find");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Value.getInstance().setValue(jTextField.getText());
//                ((JTextField)Frame.getInstance().getF().getContentPane().getComponent(3)).setText(
//                        Value.getInstance().getValue()
//                );
                Value.getInstance().setValue();

                SCamera.getInstance().setPanel(new WebcamPanel(Webcam.getWebcams().get(0)),Value.getInstance().getValue());
//                SCamera.getInstance().setPanel(null,Value.getInstance().getValue());


            }
        });



        Frame.getInstance().setF(new JFrame("Text Field Examples"));
        JFrame f = Frame.getInstance().getF();

        f.getContentPane().setLayout(new FlowLayout());
        f.getContentPane().add(jTextField);
        f.getContentPane().add(jButton);
        f.getContentPane().add(SCamera.getInstance().getPanel());
        f.getContentPane().add(jTextField1);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        System.out.println(Value.getInstance().getValue());
    }
}

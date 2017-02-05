import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import demo.Value;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

public class Client {

//    static {
//        Webcam.setDriver(new IpCamDriver());
//    }

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

//        System.setProperty("com.sun.net.ssl.checkRevocation","false");
//        IpCamDeviceRegistry.register("First", "", IpCamMode.PUSH);
//        IpCamDeviceRegistry.register("Second", "https://10.244.52.172:8080/watch/upqve07qcscz094j", IpCamMode.PUSH);

        Webcam webcam = Webcam.getWebcams().get(0);
        Webcam webcam1 = Webcam.getDefault();
        webcam1.open();


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
//                Value.getInstance().setValue();




//                SCamera.getInstance().setPanel(new WebcamPanel(Webcam.getDefault()),Value.getInstance().getValue());

//                SCamera.getInstance().setPanel(null,Value.getInstance().getValue());



            }
        });



        Frame.getInstance().setF(new JFrame("Text Field Examples"));
        JFrame f = Frame.getInstance().getF();

        SCamera.getInstance().setPanel(new WebcamPanel(webcam1));

        f.getContentPane().setLayout(new FlowLayout());
        f.getContentPane().add(jTextField);
        f.getContentPane().add(jButton);
        f.getContentPane().add(SCamera.getInstance().getPanel());
        f.getContentPane().add(jTextField1);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        Thread.sleep(10000);

//  webcam1.close();

        Frame.getInstance().getF().remove(SCamera.getInstance().getPanel());



        SwingUtilities.updateComponentTreeUI(Frame.getInstance().getF());


        System.out.println(Value.getInstance().getValue());
    }
}

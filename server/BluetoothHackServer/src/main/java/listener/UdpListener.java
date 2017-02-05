package listener;

import demo.JSONUtil;
import demo.Model;
import demo.Value;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import jdk.internal.org.objectweb.asm.tree.analysis.Frame;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.util.Objects;

/**
 * Created by Artemie on 04.02.2017.
 */
public class UdpListener {
    private MulticastSocket multicastSocket = null;
    private int BROADCAST_PORT ;
    private String BROADCAST_HOST;
    private int UDP_SERVER_PORT ;
    private String UDP_SERVER_HOST ;

    public UdpListener(int BROADCAST_PORT, String BROADCAST_HOST, int UDP_SERVER_PORT, String UDP_SERVER_HOST) throws IOException {
        this.BROADCAST_PORT = BROADCAST_PORT;
        this.BROADCAST_HOST = BROADCAST_HOST;
        this.UDP_SERVER_PORT = UDP_SERVER_PORT;
        this.UDP_SERVER_HOST = UDP_SERVER_HOST;
    }

    private void config() throws IOException {
        this.multicastSocket = new MulticastSocket(BROADCAST_PORT);
        this.multicastSocket.setBroadcast(true);
        this.multicastSocket.joinGroup(InetAddress.getByName(BROADCAST_HOST));
    }

    public void run(){
        try{
            config();
            byte[] data = null;
            JFrame frame = new JFrame("Camera-capture");
            frame.setVisible(true);
            frame.pack();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            demo.Frame.getInstance().setFrame(frame);
            JTextField jTextField = new JTextField("Hello");
            frame.add(jTextField);
            String mac = "50:2e:5c:cd:31:a2";
        while (true) {
            data = new byte[0];
            data = new byte[1024];
            DatagramPacket receiveDatagramPacket = new DatagramPacket(data, data.length);
            multicastSocket.receive(receiveDatagramPacket);

            String msg = new String(receiveDatagramPacket.getData());

            Model model = (Model) JSONUtil.getJAVAObjectfromJSONString(msg,Model.class);

            if(!Objects.equals(msg, Value.getInstance().getValue())&& Objects.equals(model.getMac(), mac)){
                jTextField.setText(msg);
                SwingUtilities.updateComponentTreeUI(demo.Frame.getInstance().getFrame());
                System.out.println(msg);
            }

            demo.Value.getInstance().setValue(msg);

        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

}

package updserver;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * Created by Artemie on 04.02.2017.
 */
public class UdpServer {

    private DatagramSocket serverSocket;

    private int UDP_PORT;
    private String UDP_HOST;

    public UdpServer(int UDP_PORT, String UDP_HOST) {
        this.UDP_PORT = UDP_PORT;
        this.UDP_HOST = UDP_HOST;
    }

    private void config() throws Exception{
//        serverSocket = new DatagramSocket(new InetSocketAddress(UDP_HOST,UDP_PORT));
        serverSocket = new DatagramSocket(UDP_PORT);
        serverSocket.setBroadcast(true);
    }

    public void run() {
        try {
            config();
            while (true) {
                System.out.println("Server receive and send");
                byte[] receiveBuff = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuff, receiveBuff.length);
                serverSocket.receive(receivePacket);
                String msg = new String(receivePacket.getData());
                DatagramPacket sendPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, new InetSocketAddress("233.0.0.1",1501));
                serverSocket.send(sendPacket);
            }
        } catch (Exception ex){
            System.out.println("exception");
        }
    }
}

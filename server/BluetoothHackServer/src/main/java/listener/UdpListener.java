package listener;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;

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
        while (true) {
            data = new byte[0];
            data = new byte[1024];
            DatagramPacket receiveDatagramPacket = new DatagramPacket(data, data.length);
            multicastSocket.receive(receiveDatagramPacket);

            String msg = new String(receiveDatagramPacket.getData());

            System.out.println(msg);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

}

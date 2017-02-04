package updserver;

import java.net.*;

/**
 * Created by Artemie on 04.02.2017.
 */
public class UdpServer {

    private DatagramSocket serverSocket;
    private DatagramSocket broadcastSocket;

    private int UDP_PORT;
    private String UDP_HOST;
    private int BROADCAST_PORT;
    private String BROADCAST_HOST;

    public UdpServer(int UDP_PORT, String UDP_HOST, int BROADCAST_PORT, String BROADCAST_HOST) {
        this.UDP_PORT = UDP_PORT;
        this.UDP_HOST = UDP_HOST;
        this.BROADCAST_PORT = BROADCAST_PORT;
        this.BROADCAST_HOST = BROADCAST_HOST;
    }

    private void config() throws Exception{
        serverSocket = new DatagramSocket(new InetSocketAddress(UDP_HOST,UDP_PORT));
//        serverSocket = new DatagramSocket(UDP_PORT);
        serverSocket.setBroadcast(true);
        broadcastSocket = new DatagramSocket(UDP_PORT+1);
        broadcastSocket.setBroadcast(true);
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
//                DatagramPacket sendPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length,new InetSocketAddress(BROADCAST_HOST,BROADCAST_PORT));
                InetAddress group = InetAddress.getByName(BROADCAST_HOST);
                DatagramPacket sendPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, group, BROADCAST_PORT);
                broadcastSocket.send(sendPacket);
            }
        } catch (Exception ex){
            System.out.println("exception");
        }
    }
}

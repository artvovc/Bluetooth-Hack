package com.example.msa.hakablue;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;

/**
 * Created by Artemie on 04.02.2017.
 */
public class Sender {

    private MulticastSocket multicastSocket = null;
    private int BROADCAST_PORT = 1505;
    private String BROADCAST_HOST = "233.0.0.1";
    private int UDP_SERVER_PORT = 8888;
    private String UDP_SERVER_HOST = "10.21.0.36";


    public Sender(int BROADCAST_PORT, String BROADCAST_HOST, int UDP_SERVER_PORT, String UDP_SERVER_HOST) throws IOException {
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

    public void request(String msg) throws Exception {
        config();
//        String msg = "message";
        try {
            DatagramPacket datagramPacket = new DatagramPacket(msg.getBytes(), msg.length(), new InetSocketAddress(this.UDP_SERVER_HOST, this.UDP_SERVER_PORT));
            this.multicastSocket.send(datagramPacket);
        } catch (Exception ex) {
            System.out.println("wrong");
        }
    }

    public static void main(String[] args) throws Exception {
//        Sender sender = new Sender(1505, "233.0.0.1", 8888, "0.0.0.0");
//        sender.request();
//        String messageStr="Hello Android!";
//        int server_port = 8888;
//        DatagramSocket s = new DatagramSocket();
//        InetAddress local = InetAddress.getByName("10.21.0.36");
//        int msg_length=messageStr.length();
//        byte[] message = messageStr.getBytes();
//        DatagramPacket p = new DatagramPacket(message, msg_length,local,server_port);
//        s.send(p);
    }

}

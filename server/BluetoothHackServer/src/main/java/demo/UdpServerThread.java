package demo;

import updserver.UdpServer;

/**
 * Created by Artemie on 04.02.2017.
 */
public class UdpServerThread {

    private static final String UDP_HOST = "10.244.52.187";
    private static final int UDP_PORT = 8888;
    private static final String BROADCAST_HOST = "233.0.0.1";
    private static final int BROADCAST_PORT = 1501;

    public static void main(String[] args) throws Exception {
//        UdpServer udpServer = new UdpServer(8888,"10.244.52.187");
        UdpServer udpServer = new UdpServer(UDP_PORT, UDP_HOST, BROADCAST_PORT, BROADCAST_HOST);
        Thread udpThread = new Thread(udpServer::run);
        udpThread.start();
        System.out.println("Server was started");
        Thread.currentThread().join();
    }
}

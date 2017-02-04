package demo;

import listener.UdpListener;
import sender.Sender;
import updserver.UdpServer;

/**
 * Created by Artemie on 04.02.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        UdpServer udpServer = new UdpServer(8888,"127.0.0.1");
        Thread udpThread = new Thread(udpServer::run);
        udpThread.start();
        System.out.println("Server was started");
        Thread.currentThread().join();
    }
}

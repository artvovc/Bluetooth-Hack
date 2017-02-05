package demo;

import listener.UdpListener;

/**
 * Created by Artemie on 04.02.2017.
 */
public class ListenerThread {

//    private static final String UDP_HOST = "10.244.52.187";
    private static final String UDP_HOST = "10.244.52.187";
    private static final int UDP_PORT = 8888;
    private static final String BROADCAST_HOST = "233.0.0.1";
    private static final int BROADCAST_PORT = 1501;

    public static void main(String[] args) throws Exception {
        System.out.println("Listener started");
        UdpListener udpListener = new UdpListener(BROADCAST_PORT,BROADCAST_HOST,UDP_PORT,UDP_HOST);
        Thread listenerThread = new Thread(udpListener::run);
        listenerThread.start();
        Thread.currentThread().join();
    }
}

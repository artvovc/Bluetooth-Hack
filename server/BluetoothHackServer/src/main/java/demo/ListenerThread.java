package demo;

import listener.UdpListener;

/**
 * Created by Artemie on 04.02.2017.
 */
public class ListenerThread {
    public static void main(String[] args) throws Exception {
        System.out.println("Listener started");
        UdpListener udpListener = new UdpListener(1501,"233.0.0.1",8888,"127.0.0.1");
        Thread listenerThread = new Thread(udpListener::run);
        listenerThread.start();
        Thread.currentThread().join();
    }
}

package demo;

import sender.Sender;

/**
 * Created by Artemie on 04.02.2017.
 */
public class SenderDemo {

    private static final String UDP_HOST = "10.21.0.36";
    private static final int UDP_PORT = 8888;
    private static final String BROADCAST_HOST = "233.0.0.1";
    private static final int BROADCAST_PORT = 1501;

    public static void main(String[] args) throws Exception {
        Sender sender = new Sender(BROADCAST_PORT, BROADCAST_HOST, UDP_PORT, UDP_HOST);
        sender.request();
    }
}

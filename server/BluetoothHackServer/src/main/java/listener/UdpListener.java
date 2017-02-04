package listener;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.util.Objects;

/**
 * Created by Artemie on 04.02.2017.
 */
public class UdpListener {
    private MulticastSocket multicastSocket = null;
    private int BROADCAST_PORT = 1501;
    private String BROADCAST_HOST = "233.0.0.1";
    private int UDP_SERVER_PORT = 8888;
    private String UDP_SERVER_HOST = "127.0.0.1";


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
//            Model model = (Model) JSONUtil.getJAVAObjectfromJSONString(msg, Model.class);
//            if (model.getWhoRequest() == WhoRequest.NODE && Objects.equals(model.getMessage(), "getCount")) {
//                setModelPack();
//                String msgJson = null;
//                this.model.setMessage("count");
//                msgJson = JSONUtil.getJSONStringfromJAVAObject(this.model);
//                DatagramPacket datagramPacket = new DatagramPacket(msgJson.getBytes(), msgJson.getBytes().length, new InetSocketAddress(this.hostname, this._UDPServer_port));
//                multicastSocket.send(datagramPacket);
//            }
//            if (Objects.equals(this.nodeId, "nodeClient") && Objects.equals(model.getMessage(), "Imaven")) {
//                this.maven = model;
//                //AICI STARTEZ TCP SERVER PE UN PORT
//
//                runTCPServer();
//
//                this.model.setMessage("getTuples");
//                String msgJson = JSONUtil.getJSONStringfromJAVAObject(this.model);
//                DatagramPacket datagramPacket = new DatagramPacket(msgJson.getBytes(), msgJson.getBytes().length, new InetSocketAddress(this.hostname, this._UDPServer_port));
//                multicastSocket.send(datagramPacket);
//                System.out.println("MAVEN " + JSONUtil.getJSONStringfromJAVAObject(maven));
//            }
//            if (Objects.equals(model.getMessage(), "sendTuplesToClient")) {
//                if (Objects.equals(this.nodeId, model.getNodeId()) || check(this.nodeId, model.getKnownNodes())) {
//                    Socket subscriberSocketClient = new Socket(model.getFromHostname(), model.getTcpServerPort());
//                    OutputStream out = subscriberSocketClient.getOutputStream();
//                    DataOutputStream DataSend = new DataOutputStream(out);
//                    String str = JSONUtil.getJSONStringfromJAVAObject(this.model);
//                    byte[] bytes = str.getBytes();
//                    DataSend.write(bytes, 0, bytes.length);
//                    out.close();
//                    DataSend.close();
//                    subscriberSocketClient.close();
//                }
//            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

}

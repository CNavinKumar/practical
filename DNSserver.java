package dns;

import java.io.*;
import java.net.*;

public class DNSserver {
    private static int indexOf(String[] array, String str) {
        str = str.trim();
        for (int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase(str))    
                return i;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        String[] hosts = {"yahoo.com", "gmail.com", "cricinfo.com", "facebook.com"};
        String[] ip = {"68.180.206.184", "209.85.148.19", "80.168.92.140", "62.63.189.16"};

        DatagramSocket serverSocket = new DatagramSocket(1362);
        System.out.println("DNS Server started...\nPress Ctrl + C to stop.");

        byte[] receiveData = new byte[1024];
        byte[] sendData;

        while (true) {
            DatagramPacket recvPacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(recvPacket);

            String receivedHost = new String(recvPacket.getData()).trim();
            InetAddress clientAddress = recvPacket.getAddress();
            int port = recvPacket.getPort();

            System.out.println("Request for host: " + receivedHost);

            String response;
            int index = indexOf(hosts, receivedHost);
            if (index != -1)
                response = ip[index];
            else
                response = "Host Not Found";

            sendData = response.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, port);
            serverSocket.send(sendPacket);
        }
    }
}


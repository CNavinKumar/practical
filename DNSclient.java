package dns;

import java.io.*;
import java.net.*;

public class DNSClient {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress ipAddress;
        if (args.length == 0)
            ipAddress = InetAddress.getLocalHost();
        else
            ipAddress = InetAddress.getByName(args[0]);

        byte[] sendData;
        byte[] receiveData = new byte[1024];

        System.out.print("Enter the host name: ");
        String hostname = br.readLine();

        sendData = hostname.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, 1362);
        clientSocket.send(sendPacket);

        DatagramPacket recvPacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(recvPacket);

        String response = new String(recvPacket.getData()).trim();
        System.out.println("IP Address: " + response);

        clientSocket.close();
    }
}

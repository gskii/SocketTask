package ru.ncedu.sockettask.udp;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Gorbatovskiy on 05.04.2016.
 */
public class Client {
    public static final int PORT = 12344;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("START CLIENT");

        byte[] buffer = new byte[1024];
        InetAddress address = InetAddress.getLocalHost();
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket outPacket = null;
        DatagramPacket inPacket = null;

        inPacket = new DatagramPacket(buffer, buffer.length);

        ObjectInputStream objectInputStream;

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        String command = null;
        while (true) {

            System.out.println("WAIT COMMAND");
            command = keyboard.readLine();

            System.out.println("SEND COMMAND");
            buffer = command.getBytes();
            outPacket = new DatagramPacket(buffer, buffer.length, address, PORT);
            socket.send(outPacket);

            System.out.print("RECEIVE RESPONSE: ");
            socket.receive(inPacket);
            objectInputStream = new ObjectInputStream(new ByteArrayInputStream(inPacket.getData()));
            Object response = objectInputStream.readObject();
            if (response.equals("close connection")) {
                System.out.println("EXIT COMMAND");
                socket.close();
                System.exit(0);
            }
            System.out.println(response.toString());
            System.out.println();
        }
    }

}

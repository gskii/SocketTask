package ru.ncedu.sockettask.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Gorbatovskiy on 05.04.2016.
 */
public class Client {

    public static final int PORT = 12344;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("START CLIENT");
        Socket socket = new Socket(InetAddress.getLocalHost(), PORT);

        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        String command = null;
        while (true) {
            System.out.println("WAIT COMMAND");
            command = keyboard.readLine();
            System.out.println("SEND COMMAND");
            outputStream.writeUTF(command);
            outputStream.flush();
            System.out.print("RECEIVE RESPONSE: ");
            Object object = inputStream.readObject();
            System.out.println(object.toString());
            System.out.println();
        }
    }
}


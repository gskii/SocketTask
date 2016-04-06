package ru.ncedu.sockettask.tcp;

import ru.ncedu.sockettask.Task;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Gorbatovskiy on 05.04.2016.
 */
public class Server {

    public static final int PORT = 12344;

    public static void main(String[] args) throws IOException {
        System.out.println("SERVER START");
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket socket = serverSocket.accept();

        System.out.println("NEW CONNECTION");
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

        String command = null;
        String[] sequence = null;
        while (true) {
            command = inputStream.readUTF();
            if (command.equals("close connection")) {
                System.out.println("EXIT COMMAND");
                System.exit(0);
            }
            sequence = command.trim().split(" ");
            if (sequence.length >= 2) {
                System.out.println("ARITHMETIC COMMAND");
                Task task = new Task(
                        Double.valueOf(sequence[0]),
                        Double.valueOf(sequence[1]));
                outputStream.writeObject(task.execute());
                outputStream.flush();
            } else {
                System.out.println("BAD COMMAND");
                outputStream.writeObject("BAD COMMAND");
                outputStream.flush();
            }
        }
    }
}

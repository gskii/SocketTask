package ru.ncedu.sockettask.udp;

import ru.ncedu.sockettask.Task;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by Gorbatovskiy on 05.04.2016.
 */
public class Server {
    private DatagramSocket socket;
    private DatagramPacket outPacket;
    private DatagramPacket inPacket;
    private InetAddress outAddress;
    private int outPort;

    public Server(int port, int size) throws SocketException {
        assert (port > 1024 && size > 0);
        byte[] buffer = new byte[size];
        socket = new DatagramSocket(port);
        inPacket = new DatagramPacket(buffer, buffer.length);
    }

    public void sendObject(Object object) throws IOException {
        ByteArrayOutputStream bytesStorageStream = new ByteArrayOutputStream();
        ObjectOutputStream serializationStream = new ObjectOutputStream(bytesStorageStream);
        serializationStream.writeObject(object);
        byte[] buffer = bytesStorageStream.toByteArray();
        outPacket = new DatagramPacket(buffer, buffer.length, outAddress, outPort);
        socket.send(outPacket);
    }

    public void handleCommand(String command) throws IOException {
        // Ловим команду на завершение работы
        if (command.equals("close connection")) {
            System.out.println("EXIT COMMAND");
            sendObject("close connection");
            socket.close();
            System.exit(0);
        }

        // Делим команду на составляющие
        String[] sequence = command.split(" ");
        if (sequence.length == 2) {
            System.out.println("ARITHMETIC COMMAND");
            Task task = new Task(
                    Double.valueOf(sequence[0]),
                    Double.valueOf(sequence[1]));
            sendObject(task.execute());
        } else {
            System.out.println("BAD COMMAND");
            sendObject("BAD COMMAND");
        }
    }

    public void listen() throws IOException, InterruptedException {
        System.out.println("SERVER START");
        while (true) {
            // Если не будем ждать, поймаем свой же пакет
            Thread.sleep(1000);
            socket.receive(inPacket);
            outAddress = inPacket.getAddress();
            outPort = inPacket.getPort();
            handleCommand(new String(inPacket.getData()).trim());
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        new Server(12344, 1024).listen();
    }
}
package CHATUDP_JDBC;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class CLient {
    public static void main(String[] args) throws IOException {
        int port_server = 1111;
        String ip = "localhost";
        DatagramSocket client_socket = new DatagramSocket();

        while (true){
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            sendDataFromServer(input, ip, port_server, client_socket);
            if ("exit".equalsIgnoreCase(input)){
                client_socket.close();
                break;
            }
            recevieDataFromServer(client_socket);
        }
    }

    private static void recevieDataFromServer(DatagramSocket client_socket) throws IOException {
        byte[] bytes = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(bytes, bytes.length);
        client_socket.receive(receivePacket);
        String mesage = new String(receivePacket.getData(),0,receivePacket.getLength());
        System.out.println(mesage);
    }

    private static void sendDataFromServer(String input, String ip, int port_server, DatagramSocket client_socket) throws IOException {
        byte[] sendData = input.getBytes();
        InetAddress inetPort = InetAddress.getByName(ip);
        DatagramPacket client_packet = new DatagramPacket(sendData,sendData.length,inetPort, port_server);
        client_socket.send(client_packet);
    }

}

package CHATUDP_JDBC;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {
        int port_server = 1111;
        DatagramSocket server_socket = new DatagramSocket(port_server);
        System.out.println("Server is running and waiting for client...");
        while (true){
            DatagramPacket receivePacket = recevieDataFromClient(server_socket);
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if ("exit".equalsIgnoreCase(input)){
                server_socket.close();
                break;
            }

            sendDataToClient(receivePacket, input, server_socket);

        }




    }

    private static void sendDataToClient(DatagramPacket receivePacket, String input, DatagramSocket server_socket) throws IOException {
        InetAddress clientAddress = receivePacket.getAddress();
        byte[] sendData = input.getBytes();
        int clientPort = receivePacket.getPort();
        DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,clientAddress,clientPort);
        server_socket.send(sendPacket);
    }

    private static DatagramPacket recevieDataFromClient(DatagramSocket server_socket) throws IOException {
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        server_socket.receive(receivePacket);
        String mesage = new String(receivePacket.getData(),0,receivePacket.getLength());
        System.out.println(mesage);
        return receivePacket;
    }


}

package ChatTCP_JDBC;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Server extends Thread{
    public static void main(String[] args) throws IOException, SQLException {
        int serverPort = 8888;
        ServerSocket serverSocket = new ServerSocket(serverPort);
        System.out.println("ChatTCP_JDBC.Server in running....");
        Socket socket = serverSocket.accept();



        while (true){
            repDataFromClient(socket);
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input)){
                serverSocket.close();
                socket.close();
                break;
            }
            sendDataToClient(socket,input);
        }
    }

    private static void repDataFromClient(Socket socket) throws IOException, SQLException {
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int readByte = inputStream.read(bytes);
        String repByte = new String(bytes,0,readByte);
        System.out.println(repByte);
    }
    private static void sendDataToClient(Socket socket , String input) throws IOException, SQLException {
            OutputStream outputStream = socket.getOutputStream();
            byte[] sendData = input.getBytes();
            outputStream.write(sendData);
            String server = "Server";
            String id_server = "Sv001";
            saveDataToJDBC(id_server,server,input);
    }
    private static void saveDataToJDBC(String id , String nameData,String saveData) throws SQLException {
        Connect_JDBC connectJdbc = new Connect_JDBC();
        Connection conn = connectJdbc.connect();

        String query = "INSERT INTO SAVE VALUES(?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1,id);
        preparedStatement.setString(2,nameData);
        preparedStatement.setString(3,saveData);
        preparedStatement.executeUpdate();
        conn.close();
    }
}

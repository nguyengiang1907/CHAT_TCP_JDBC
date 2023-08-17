package ChatTCP_JDBC;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException, SQLException {
        String ipServer = "localhost";
        int serverPort = 8888;
        Socket socket = new Socket(ipServer,serverPort);

        while (true){
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            sendDataToServer(socket, input);
            repDataForServer(socket);
            if ("exit".equalsIgnoreCase(input)){
                socket.close();
                break;
            }
        }
    }

    private static void sendDataToServer(Socket socket,String input) throws IOException, SQLException {
        OutputStream outputStream = socket.getOutputStream();
        byte[] writeData = input.getBytes();
        outputStream.write(writeData);
        String id = "Cl001";
        String name = "Client";

        saveDataToSQL(id,name,input);
    }
    private static void repDataForServer(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int readData = inputStream.read(bytes);
        String repData = new String(bytes,0,readData);
        if ("exit".equalsIgnoreCase(repData)){
            socket.close();
        }
        System.out.println(repData);

    }
    private static void saveDataToSQL(String id,String name,String data) throws SQLException {
        Connect_JDBC connectJdbc = new Connect_JDBC();
        Connection coon = connectJdbc.connect();

        String query = "INSERT INTO SAVE VALUES(?,?,?)";
        PreparedStatement prtm = coon.prepareStatement(query);
        prtm.setString(1,id);
        prtm.setString(2,name);
        prtm.setString(3,data);
        prtm.executeUpdate();
        coon.close();
    }

}

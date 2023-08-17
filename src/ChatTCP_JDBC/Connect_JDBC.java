package ChatTCP_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect_JDBC {
    private String hostName = "localhost:3306";
    // Khai báo biến chứa tên DataBase
    private String dbName = "CHAT_TCP_JDBC";
    // Khai báo biến chứa tên tài khoản
    private String userName = "root";
    // Khai báo biến chứa mật khẩu tài khoản
    private String password = "giang";
    // Khai báo biến chứa kết đến mysql có qua hostName và tên database
    private String connectionURL = "jdbc:mysql://"+hostName+"/"+dbName;


    public Connection connect () throws SQLException {
        // dùng connection để kết kết nối Database
        Connection conn = null;
        // liên kết thông qua url và tài khoản và mật khẩu của cơ sở dử liệu
        // DriverManager được dùng để kết nối với CSDL
        conn = DriverManager.getConnection(connectionURL,userName,password);

        // trả về đối tượng connection
        return conn;
    }
}

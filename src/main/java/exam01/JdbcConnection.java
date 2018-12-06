package exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
public class JdbcConnection {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER_NAME = "scott";
    private static final String PASSWORD = "tiger";

    private static Connection connection = null;
    private static Statement statement = null;

    public static void main(String[] args) {
        try{
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            statement = connection.createStatement();
            System.out.println("Connection Opened");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Connection is not Opened ! " + e.getMessage());
        }

    }

}

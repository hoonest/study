package exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;
public class JdbcDelete {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER_NAME = "scott";
    private static final String PASSWORD = "tiger";

    private static Connection connection = null;
    private static Statement statement = null;

    public static void main(String[] args) {
        String sql = "";

        Scanner input = new Scanner(System.in);

        try{
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            statement = connection.createStatement();
            System.out.println("Connection Opened");

            System.out.println(" ***회원 삭제 기능 *** ");
            System.out.print(" 삭제할 회원 번호 입력 : ");
            String no = input.nextLine();

            sql = "delete from customer where no = " + no;

            int result = statement.executeUpdate(sql);

            if(result == 1) {
                System.out.println("성공 ");
            } else {
                System.out.println("실패 ");
            }

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Connection is not Opened ! " + e.getMessage());
        } finally {
            try {
                if(connection != null) {connection.close();	}
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

package exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class JdbcInsert {
        private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
        private static final String USER_NAME = "scott";
        private static final String PASSWORD = "tiger";

        private static Connection connection = null;
        private static PreparedStatement pstmt = null;

        public static void main(String[] args) {
            String sql = "";

            Scanner input = new Scanner(System.in);

            try{
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                //statement = connection.createStatement();
                System.out.println("Connection Opened");

                System.out.println(" ***회원 입력 기능 *** ");
                System.out.print(" 번호 입력 : ");
                String no = input.nextLine();
                System.out.print(" 이름 입력 : ");
                String name = input.nextLine();
                System.out.print(" 이메일 입력 : ");
                String email = input.nextLine();
                System.out.print(" 전화번호 입력 : ");
                String tel = input.nextLine();

                sql = "insert into customer values(?, ?, ?, ?)";
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(no));
                pstmt.setString(2, name);
                pstmt.setString(3, email);
                pstmt.setString(4, tel);


                //int result = statement.executeUpdate(sql);
                int rs = pstmt.executeUpdate();

                if(rs > 0) {
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


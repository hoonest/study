package exam01;

import javax.swing.*;
import java.sql.*;
import java.util.Scanner;

public class JdbcUpdate {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER_NAME = "scott";
    private static final String PASSWORD = "tiger";

    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement pstmt = null;

    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);

        try{

            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            //statement = connection.createStatement();
            System.out.println("Connection Opened");

            String sql = "";

            System.out.println(" ***회원 수정 기능 *** ");
            System.out.print(" 번호 입력 : ");
            String no = input.nextLine();
            System.out.print(" 이름 입력 : ");
            String name = input.nextLine();
            System.out.print(" 이메일 입력 : ");
            String email = input.nextLine();
            System.out.print(" 전화번호 입력 : ");
            String tel = input.nextLine();

            sql = "update customer set name=?, email=?, tel=? where no=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, tel);
            pstmt.setInt(4, Integer.parseInt(no));


            //int result = statement.executeUpdate(sql);
            int rs = pstmt.executeUpdate();

            if(rs == 0) {
                System.out.println("성공 ");
            } else {
                System.out.println("실패 ");
            }

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Connection is not Opened ! " + e.getMessage());
        } finally {
            try {
                if(pstmt != null) {pstmt.close();	}
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

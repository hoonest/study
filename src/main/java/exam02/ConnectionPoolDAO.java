package exam02;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPoolDAO {


    BasicDataSource ds;
        ConnectionPoolDAO(){
            ds = new BasicDataSource();
            ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            ds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
            ds.setUsername("scott");
            ds.setPassword("tiger");
            ds.setInitialSize(5);
        }

    public List<ConnectionPoolDTO> select() {
        List<ConnectionPoolDTO> dtoList = new ArrayList<ConnectionPoolDTO>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn =  ds.getConnection();
            String sql = " select * from customer";
            pstmt = conn.prepareStatement(sql);
            rs= pstmt.executeQuery();

            while(rs.next()) {
                ConnectionPoolDTO dto = new ConnectionPoolDTO();
                dto.setNo(rs.getInt("no"));
                dto.setName(rs.getString("name"));
                dto.setEmail(rs.getString("email"));
                dto.setTel(rs.getString("tel"));
                dtoList.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

        return dtoList;
    }

    public int insert(ConnectionPoolDTO dto) {
        Connection conn = null;
        String sql = "insert into customer values(?,?,?,?)";
        PreparedStatement pstmt = null;
        int iResult = 0;


        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, dto.getNo());
            pstmt.setString(2, dto.getName());
            pstmt.setString(3, dto.getEmail());
            pstmt.setString(4, dto.getTel());
            iResult = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return iResult;
    }

    public int update(ConnectionPoolDTO dto) {
        Connection conn = null;
        String sql = "update customer set name=?, email=?, tel=? where no=?)";
        PreparedStatement pstmt = null;
        int iResult = 0;

        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getName());
            pstmt.setString(2, dto.getEmail());
            pstmt.setString(3, dto.getTel());
            pstmt.setInt(4, dto.getNo());
            iResult = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return iResult;
    }


    public int delete(int no) {
        Connection conn = null;
        String sql = "delete  customer where no=?)";
        PreparedStatement pstmt = null;
        int iResult = 0;

        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no);
            iResult = pstmt.executeUpdate();

            if(iResult == 1) {
                System.out.println("회원삭제 성공");
            } else {
                System.out.println("회원삭제 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstmt != null) { pstmt.close(); }
                if(conn != null) { conn.close(); }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return iResult;
    }
}

package exam02;

import java.util.List;
import java.util.Scanner;

public class ConnectionPoolTest {









    public static void main(String[] args) {
        ConnectionPoolDAO dao = new ConnectionPoolDAO();
        Scanner input = new Scanner(System.in);

        while(true) {
            System.out.println("\n\n*** 메뉴 구성 ***");
            System.out.println("1. 프로그램 종료.");
            System.out.println("2. 데이터 저장하기.");
            System.out.println("3. 데이터 읽어오기.");
            System.out.println("4. 데이터 수정하기.");
            System.out.println("5. 데이터 삭제하기.");
            System.out.print(">> 메뉴 선택 : ");

            int choice = input.nextInt();

            switch(choice) {
                case 1:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                case 2:
                    insert(dao);
                    break;
                case 3:
                    select(dao);
                    break;
                case 4:
                    update(dao);
                    break;
                case 5:
                    delete(dao);
                    break;
                default:
                    break;
            }

        }
    }

    public static void select(ConnectionPoolDAO dao) {
        List<ConnectionPoolDTO> list = null;
        list = dao.select();

        System.out.println("번호 \t  이름 \t\t\t 이메일 \t\t 전화번호");
        System.out.println("--------------------------------------------------------");

        for(ConnectionPoolDTO dto: list) {
            int no = dto.getNo();
            String name = dto.getName();
            String email = dto.getEmail();
            String tel = dto.getTel();

            System.out.printf("%d \t %s \t %s \t %s\n",
                    no, name, email, tel);
        }
    }

    public static void insert(ConnectionPoolDAO dao) {
        Scanner input = new Scanner(System.in);
        ConnectionPoolDTO dto = new ConnectionPoolDTO();

        System.out.println("테이블에 저장할 값 입력 받기...");
        System.out.print("번호 입력 : ");
        String no = input.nextLine();
        int chageNo = Integer.parseInt(no);
        System.out.print("이름 입력 : ");
        String name = input.nextLine();
        System.out.print("이메일 입력 : ");
        String email = input.nextLine();
        System.out.print("전화 번호 입력 : ");
        String tel = input.nextLine();

        dto.setNo(chageNo);
        dto.setName(name);
        dto.setEmail(email);
        dto.setTel(tel);

        dao.insert(dto);
    }

    public static void update(ConnectionPoolDAO dao) {
        Scanner input = new Scanner(System.in);
        ConnectionPoolDTO dto = new ConnectionPoolDTO();

        System.out.println("회원 정보 수정...");
        System.out.print("수정할 회원번호를 입력하세요:");
        // int no = input.nextInt();
        String no = input.nextLine();
        int chgNo = Integer.parseInt(no);
        System.out.print("수정할 이름을 입력하세요:");
        String name = input.nextLine();
        System.out.print("수정할 이메일을 입력하세요:");
        String email = input.nextLine();
        System.out.print("수정할 전화번호를 입력하세요:");
        String tel = input.nextLine();

        dto.setNo(chgNo);
        dto.setName(name);
        dto.setEmail(email);
        dto.setTel(tel);

        dao.update(dto);
    }

    public static void delete(ConnectionPoolDAO dao) {
        Scanner input = new Scanner(System.in);

        System.out.println("*** 회원 삭제 기능 ***");
        System.out.print("삭제할 회원 번호 입력 : ");
        String no = input.nextLine();
        int chgNo = Integer.parseInt(no);

        dao.delete(chgNo);
    }
}

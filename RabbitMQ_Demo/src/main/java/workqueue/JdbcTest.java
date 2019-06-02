package workqueue;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTest {
    public static void main(String[] args) throws Exception {
        String URL="jdbc:mysql://139.196.94.253:3306/jpa";
        Class.forName("com.mysql.cj.jdbc.Driver");
       //2.获得数据库链接
       Connection conn = DriverManager.getConnection(URL, "root", "123");
        System.out.println(conn.getCatalog());
    }
}

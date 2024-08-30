import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class test {

    @Test
    public void testConnection() {
        String url = "jdbc:mysql://mybreadb.c5cse4w4q83z.ap-northeast-2.rds.amazonaws.com:3306/breadb";
        String user = "euna";
        String password = "breadb123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            System.out.println("==========[ JDBCTest() ]==========");
            System.out.println("Connection Successful");
            System.out.println("Connection : " + conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
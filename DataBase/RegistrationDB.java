package DataBase;

import java.sql.*;

/**
 * Created by Vladislav on 3/6/2017.
 */
public class RegistrationDB {

    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    public static void CreateDB() throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:Catalogue.s3db");
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'users' ('Email' text, 'Password' text, 'Role' text, 'UpdateInf' text);");
    }

    public static void WriteDB() throws SQLException {
        try {
            statmt.execute("INSERT INTO 'users' ('FirstName', 'LastName', 'Email', 'Password') VALUES ('LLL', 'Lesh', 'vlad.w.lesh', '123'); ");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void CloseDB() throws ClassNotFoundException, SQLException {
        conn.close();
        statmt.close();
        resSet.close();

    }

}

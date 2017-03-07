package DataBase;

import GUI.RegistrationGUI.RegistrationData;

import java.sql.*;

public class RegistrationDB {

    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    public static void createDB() throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:Catalogue.s3db");
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'users' ('Email' text, 'Password' text, 'Status' text, 'UpdateInf' text);");
    }

    public static void writeDB(RegistrationData registrationData) throws SQLException,ClassNotFoundException {
        createDB();
        try {
            statmt.execute("INSERT INTO 'users' ('Email', 'Password', 'Status', 'UpdateInf') VALUES ('"+
                    registrationData.getEmail().getText()+"', '"+registrationData.getEncryptPassword()+"', 'User', '0'); ");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
        statmt.close();
    }

    public static boolean checkLogin(String login) throws SQLException,ClassNotFoundException {
        createDB();
        resSet = statmt.executeQuery("SELECT * FROM users");

        while (resSet.next()) {
            String name = resSet.getString("Email");
            if(login.equals(name)) {
                closeDB();
                return false;
            }
        }
        closeDB();
        return true;
    }

    public static void closeDB() throws ClassNotFoundException, SQLException {
        conn.close();
        statmt.close();
        resSet.close();
    }

}

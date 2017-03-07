package DataBase;

import GUI.RegistrationGUI.RegistrationData;
import Launch.User;
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
        statmt.execute("CREATE TABLE if not exists 'users' ('Email' text, 'Password' text, 'Status' text, 'UpdateInf' text, 'Size' DOUBLE );");
    }

    public static void writeDB(RegistrationData registrationData, String date) throws SQLException,ClassNotFoundException {
        createDB();

        try {
            statmt.execute("INSERT INTO 'users' ('Email', 'Password', 'Status', 'UpdateInf', 'Size') VALUES ('"+
                    registrationData.getEmail().getText()+"', '"+registrationData.getEncryptPassword()+"', 'User','"+date+"', '10'); ");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
        statmt.close();
    }

    public static void adminWriteDB(String login, String password) throws SQLException,ClassNotFoundException {
        createDB();

        try {
            statmt.execute("INSERT INTO 'users' ('Email', 'Password', 'Status') VALUES ('"+
                    login+"', '"+password+"', 'Admin'); ");
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

    public static User checkSingIn(String login, String password) throws SQLException,ClassNotFoundException {
        createDB();
        resSet = statmt.executeQuery("SELECT * FROM users");
        User user = new User();
        while (resSet.next()) {
            String name = resSet.getString("Email");
            String passw = resSet.getString("Password");

            if(login.equals(name) && password.equals(passw)) {
                 user = new User(login,resSet.getString("Status"),
                        resSet.getString("UpdateInf"),resSet.getDouble("Size"));
            }
        }
        closeDB();

        return user;
    }

    public static void overwriting(User user) throws SQLException,ClassNotFoundException {
        createDB();
        String sql = "UPDATE users SET UpdateInf = ? , Size = ? " + "WHERE Email = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, user.getUpDateInf());
        preparedStatement.setDouble(2, user.getSize());
        preparedStatement.setString(3, user.getLogin());
        preparedStatement.execute();
        preparedStatement.close();
    }



    public static void closeDB() throws ClassNotFoundException, SQLException {
        conn.close();
        statmt.close();
        resSet.close();
    }

}

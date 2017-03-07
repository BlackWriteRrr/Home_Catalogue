package Launch;

/**
 * Created by Vladislav on 3/7/2017.
 */
public class User {
    private String login;
    private String status;
    private String upDateInf;
    private double size;

    public User(){}

    public User(String log, String stat, String upD, double siz){
        login=log;
        status=stat;
        upDateInf=upD;
        size=siz;
    }

    public String getLogin(){
        return login;
    }

    public String getStatus(){
        return status;
    }

    public String getUpDateInf(){
        return upDateInf;
    }

    public double getSize(){
        return size;
    }

    public void setSize(double Size){
        size=Size;
    }

    public void setUpDateInf(String date){
        upDateInf=date;
    }
}

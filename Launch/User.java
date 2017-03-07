package Launch;

/**
 * Created by Vladislav on 3/7/2017.
 */
public class User {
    private String login;
    private String status;
    private String upDateInf;

    public User(String log, String stat, String upD){
        login=log;
        status=stat;
        upDateInf=upD;
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
}

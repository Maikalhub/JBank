package condorcet.Models.Entities;

import java.util.HashSet;
import java.util.Set;



public class UserA
{

    private int id;

    private String login;

    private String password;

    public UserA() {
    }

    public UserA(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

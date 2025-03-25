package main.Data;

public class AdminA
{

    private int id;

    private String login;

    private String password;



    // Default constructor
    public AdminA()
    {

    }


    // Constructor with parameters
    public AdminA(int id, String login, String password)
    {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    // Getters and setters

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

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

}


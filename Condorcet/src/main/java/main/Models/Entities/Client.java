package main.Models.Entities;


public class Client {
    private String login;
    private String password;

    // Default constructor
    public Client() {}

    // Constructor with parameters
    public Client(String login, String password) {
        this.login = login;
        this.password = password;
    }

    // Getters and Setters
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



    // Optional: Override toString(), equals(), hashCode() if needed
}

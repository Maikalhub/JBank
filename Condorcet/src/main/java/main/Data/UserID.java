package main.Data;

public class UserID {


    private int id;

    private String login;

    private String password;

    private String fio;

    private String role;

    private String status;

    private Double salary;

    private String currency;

    private String groups;

    // Default constructor
    public UserID(int id, String login_datagroups_, String password_datagroups_, String fio_datagroups_, String post_datagroups_, String sdstatus_datagroups_, Double salary_datagroups_, String byr, String numbergroups_datagroups_) {}

    // Constructor with parameters
    public UserID(int id, String login, String password ,String fio, String role, String status, String currency, Double salary, String groups) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.currency = currency;
        this.salary = salary;
        this.groups = groups;
    }

    // Getters and setters for each field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setSalary(Double salary) {this.salary = salary;}

    public double getSalary() {
        return salary;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }
}

package condorcet.Models.Entities;

public class AdminID {

    private int id;
    private String login;
    private String password;
    private String fio;
    private String role;
    private String status;
    private Double salary;
    private String currency;
    private String groups;

    // Конструктор с параметрами
    public AdminID(String login, String password, String fio, String role,
                   String status, Double salary, String currency, String groups) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.salary = salary;
        this.currency = currency;
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

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }
}

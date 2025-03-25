package condorcet.Services;

import condorcet.DataAccessObjects.AdminIDDAO;
import condorcet.DataAccessObjects.UserADAO;
import condorcet.DataAccessObjects.UserIDDAO;
import condorcet.Models.Entities.AdminID;
import condorcet.Models.Entities.Client;
import condorcet.Models.Entities.UserA;
import condorcet.Models.Entities.UserID;

public class AdminIDService
{

    Client client;
    private AdminIDService adminIDService;


    private AdminIDDAO adminIDDAO = new AdminIDDAO();

    public AdminIDDAO getAdminIDDAO() {
        return adminIDDAO;
    }

    public void setAdminIDDAO(AdminIDDAO adminIDDAO) {
        this.adminIDDAO = adminIDDAO;
    }

    // Метод для добавления пользователя
    public boolean UpadateadminBylogin(String login,String password, String fio, String role, String status, double salary, String currency, String groups) {
        // Создаем нового пользователя
        AdminID newUser2 = new AdminID(login,password,fio,role,status,salary,currency,groups);
        newUser2.setLogin(login);
        newUser2.setPassword(password);
        newUser2.setFio(fio);
        newUser2.setRole(role);
        newUser2.setStatus(status);
        newUser2.setSalary(salary);
        newUser2.setCurrency(currency);
        newUser2.setGroups(groups);

        // Добавляем нового пользователя в базу данных через DAO
        return adminIDDAO.updateAdminByLogin(newUser2);  // Метод addUserID в DAO
    }



}

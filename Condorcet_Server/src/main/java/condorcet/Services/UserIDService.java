package condorcet.Services;

import condorcet.DataAccessObjects.UserIDDAO;
import condorcet.Models.Entities.Client;
import condorcet.Models.Entities.UserID;

public class UserIDService

{
    Client client;
    private UserIDService userIDService;

    private UserIDDAO userIDDAO = new UserIDDAO();  // Создаем объект DAO для работы с базой данных

    // Метод для добавления пользователя
    public boolean addUserID(String login, String password, String fio, String role, String status, double salary, String currency, String groups) {
        // Создаем нового пользователя
        UserID newUser = new UserID();
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setFio(fio);
        newUser.setRole(role);
        newUser.setStatus(status);
        newUser.setSalary(salary);
        newUser.setCurrency(currency);
        newUser.setGroups(groups);

        // Добавляем нового пользователя в базу данных через DAO
        return userIDDAO.addUser(newUser);  // Метод addUserID в DAO
    }

    // Метод для добавления пользователя
    public boolean UpadateuserBylogin(String login, String password, String fio, String role, String status, double salary, String currency, String groups) {
        // Создаем нового пользователя
        UserID newUser2 = new UserID();
        newUser2.setLogin(login);
        newUser2.setPassword(password);
        newUser2.setFio(fio);
        newUser2.setRole(role);
        newUser2.setStatus(status);
        newUser2.setSalary(salary);
        newUser2.setCurrency(currency);
        newUser2.setGroups(groups);

        // Добавляем нового пользователя в базу данных через DAO
        return userIDDAO.updateUserByLogin(newUser2);  // Метод addUserID в DAO
    }




    // Геттер и сеттер для userIDService
    public UserIDService getUserIDService()
    {
        return userIDService;
    }

    public void setUserIDService(UserIDService userIDService) {
        this.userIDService = userIDService;
    }

    public boolean UpadateuserBylogin2(String fio, String groups)
    {
        // Создаем нового пользователя
        UserID newUser2 = new UserID();
        newUser2.setFio(fio);
        newUser2.setGroups(groups);

        // Добавляем нового пользователя в базу данных через DAO
        return userIDDAO.updateUserByLogin2(newUser2);  //
    }
}

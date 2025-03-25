package condorcet.Services;

import condorcet.DataAccessObjects.UserADAO;
import condorcet.Models.Entities.Client;
import condorcet.Models.Entities.UserA;

public class UserAService
{

    Client client;
    private UserAService userAService;


    private UserADAO userADAO = new UserADAO();





    public UserA findByLoginAndPassword(String login, String password)
    {
        return userADAO.findByLoginAndPassword(login, password);
    }

    public boolean editUserA(UserA updatedUser) {
        return false;
    }

    public boolean deleteUserA(int userIdToDelete) {
        return false;
    }

    public boolean updateUserA(UserA updatedUserData) {
        return false;
    }

    // Метод для добавления пользователя
    public boolean addUserA(String login, String password)
    {
        // Логика добавления пользователя в базу данных
        // Например, можно проверить, существует ли уже пользователь, и добавить нового
        UserA newUser = new UserA();
        newUser.setLogin(login);
        newUser.setPassword(password);

        return userADAO.addUserA(newUser);  // Вызов метода из DAO для добавления
    }

    public UserAService getUserAService() {
        return userAService;
    }

    public void setUserAService(UserAService userAService) {
        this.userAService = userAService;
    }
}

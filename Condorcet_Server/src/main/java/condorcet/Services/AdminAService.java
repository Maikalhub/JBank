package condorcet.Services;

import condorcet.DataAccessObjects.AdminADAO;
import condorcet.Models.Entities.AdminA;

import java.util.List;

public class AdminAService
{

    private AdminADAO adminADAO = new AdminADAO();

    public AdminA findByLoginAndPassword(String login, String password)
    {
        return adminADAO.findByLoginAndPassword(login, password);
    }

    public boolean addAdminA(AdminA newAdmin) {
        return false;
    }

    public boolean editAdminA(AdminA updatedAdmin) {
        return false;
    }

    public boolean deleteAdminA(int adminIdToDelete) {
        return false;
    }

    public boolean updateAdminA(List<AdminA> updatedAdminData)
    {
        return false;
    }

    // Метод для обновления данных администратора
    public boolean updateAdminA(AdminA adminA)
    {
        // Логика для обновления данных администратора в базе данных
        return adminADAO.update(adminA); // Предположим, что этот метод существует в DAO
    }


    // Метод для получения всех администраторов
    public List<AdminA> getAllAdmins()
    {
        return adminADAO.findAll(); // Получаем всех администраторов
    }

}

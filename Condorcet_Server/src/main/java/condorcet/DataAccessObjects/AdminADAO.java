package condorcet.DataAccessObjects;

import condorcet.Models.Entities.AdminA;
import condorcet.Models.Entities.AdminA;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminADAO
{

    // Метод для получения всех администраторов
    public List<AdminA> findAll()
    {
        List<AdminA> admins = new ArrayList<>();
        String query = "SELECT * FROM Admin_A"; // Запрос на получение всех администраторов

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Обрабатываем результаты
            while (resultSet.next()) {
                AdminA admin = new AdminA();
                admin.setId(resultSet.getInt("ID"));
                admin.setLogin(resultSet.getString("Логин"));
                admin.setPassword(resultSet.getString("Пароль"));
                // Дополнительные поля, если они есть
                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }

    // Метод для обновления данных администратора
    public boolean update(AdminA admin) {
        String query = "UPDATE Admin_A SET Логин = ?, Пароль = ? WHERE ID = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, admin.getLogin());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setInt(3, admin.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0; // Возвращаем true, если данные были обновлены
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAll(List<AdminA> admins) {
        String query = "UPDATE Admin_A SET Логин = ?, Пароль = ? WHERE ID = ?";
        boolean success = true;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (AdminA admin : admins) {
                preparedStatement.setString(1, admin.getLogin());
                preparedStatement.setString(2, admin.getPassword());
                preparedStatement.setInt(3, admin.getId());

                int rowsUpdated = preparedStatement.executeUpdate();
                success &= rowsUpdated > 0;  // Если хотя бы один администратор не был обновлен, возвращаем false
            }
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }

        return success;
    }





    // Метод для поиска пользователя по логину и паролю
    public AdminA findByLoginAndPassword(String login, String password)
    {
        String query = "SELECT * FROM Admin_A WHERE Логин = ? AND Пароль = ?";
        AdminA user = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query))
        {

            preparedStatement.setString(1, login); // Устанавливаем параметр для логина
            preparedStatement.setString(2, password); // Устанавливаем параметр для пароля

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new AdminA();
                user.setId(resultSet.getInt("ID"));
                user.setLogin(resultSet.getString("Логин"));
                user.setPassword(resultSet.getString("Пароль"));
                // Дополнительные поля, если они есть
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}

package condorcet.DataAccessObjects;

import condorcet.Models.Entities.UserA;
import condorcet.DataAccessObjects.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserADAO {

    // Метод для получения всех пользователей
    public List<UserA> findAll()
    {
        List<UserA> users = new ArrayList<>();
        String query = "SELECT * FROM User_A"; // Запрос на получение всех пользователей

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Обрабатываем результаты
            while (resultSet.next()) {
                UserA user = new UserA();
                user.setId(resultSet.getInt("ID"));
                user.setLogin(resultSet.getString("Логин"));
                user.setPassword(resultSet.getString("Пароль"));
                // Дополнительные поля, если они есть
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    // Метод для поиска пользователя по логину и паролю
    public UserA findByLoginAndPassword(String login, String password)
    {

        String query = "SELECT * FROM User_A WHERE Логин = ? AND Пароль = ?";
        UserA user = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, login); // Устанавливаем параметр для логина
            preparedStatement.setString(2, password); // Устанавливаем параметр для пароля

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new UserA();
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

    // Метод для добавления пользователя в базу данных
    public boolean addUserA(UserA user) {
        String query = "INSERT INTO User_A (Логин, Пароль) VALUES (?, ?)"; // Запрос на добавление пользователя

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Устанавливаем параметры запроса
            preparedStatement.setString(1, user.getLogin());   // Логин
            preparedStatement.setString(2, user.getPassword()); // Пароль

            // Выполняем запрос на добавление в базу данных
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;  // Если хотя бы одна строка была добавлена, возвращаем true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




}

package condorcet.DataAccessObjects;

import condorcet.Models.Entities.UserID;
import java.sql.*;
import java.math.BigDecimal;

public class UserIDDAO {

    public boolean addUser(UserID user)
    {
        // SQL-запрос для вставки данных
        String query = "INSERT INTO UserID (Логин, Пароль, ФИО, Должность, Статус, Зарплата, КодВалюты, НомерГруппы) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Устанавливаем параметры запроса
            preparedStatement.setString(1, user.getLogin());// Логин
            preparedStatement.setString(2, user.getPassword()); // Логин
            preparedStatement.setString(3, user.getFio()); // ФИО
            preparedStatement.setString(4, user.getRole()); // Должность
            preparedStatement.setString(5, user.getStatus()); // Статус

            // Преобразуем зарплату в BigDecimal и устанавливаем ее
            BigDecimal salary = BigDecimal.valueOf(user.getSalary()); // Если salary - это double, преобразуем в BigDecimal
            preparedStatement.setBigDecimal(6, salary); // Зарплата

            preparedStatement.setString(7, user.getCurrency()); // Код валюты
            preparedStatement.setString(8, user.getGroups()); // Номер группы

            // Выполняем запрос на добавление в базу данных
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;  // Возвращаем true, если хотя бы одна строка была добавлена

        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Возвращаем false, если произошла ошибка
        }
    }

    // Метод для обновления всех данных пользователя по логину
    public boolean updateUserByLogin(UserID user)
    {
        String query = "UPDATE UserID SET ФИО = ?, Должность = ?, Статус = ?, Зарплата = ?, КодВалюты = ?, НомерГруппы = ? WHERE Логин = ?";

        String status = user.getStatus();
        System.out.println("-----");
        System.out.println(status);


        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {


            // Устанавливаем параметры запроса для обновления
            preparedStatement.setString(1, user.getFio()); // ФИО
            preparedStatement.setString(2, user.getRole()); // Должность
            preparedStatement.setString(3, user.getStatus()); // Статус

            BigDecimal salary = BigDecimal.valueOf(user.getSalary()); // Преобразуем зарплату в BigDecimal
            preparedStatement.setBigDecimal(4, salary); // Зарплата

            preparedStatement.setString(5, user.getCurrency()); // Код валюты
            preparedStatement.setString(6, user.getGroups()); // Номер группы
            preparedStatement.setString(7, user.getLogin()); // Логин для поиска

            // Выполняем запрос на обновление
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Возвращаем true, если хотя бы одна строка была обновлена
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Возвращаем false, если произошла ошибка
        }
    }

    public boolean updateUserByLogin2(UserID user) {
        // SQL запрос для обновления данных по логину (ФИО и НомерГруппы)
        String query = "UPDATE UserID SET НомерГруппы = ? WHERE ФИО = ?";

        // Логируем ФИО и Номер группы для проверки
        System.out.println("-----");
        System.out.println("ФИО: " + user.getFio());
        System.out.println("Номер группы: " + user.getGroups());

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Устанавливаем параметры запроса для обновления
            preparedStatement.setString(1, user.getGroups()); // НомерГруппы
            preparedStatement.setString(2, user.getFio());   // ФИО

            // Выполняем запрос на обновление
            int rowsAffected = preparedStatement.executeUpdate();

            // Проверяем, были ли обновлены строки
            return rowsAffected > 0; // Возвращаем true, если хотя бы одна строка была обновлена

        } catch (SQLException e) {
            e.printStackTrace(); // Логируем ошибку
            return false; // Возвращаем false в случае ошибки
        }
    }



}

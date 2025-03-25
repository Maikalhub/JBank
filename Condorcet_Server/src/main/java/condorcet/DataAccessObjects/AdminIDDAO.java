package condorcet.DataAccessObjects;

import condorcet.Models.Entities.AdminID;
import condorcet.Models.Entities.UserID;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminIDDAO
{
    // Метод для обновления всех данных пользователя по логину
    public boolean updateAdminByLogin(AdminID adminID)
    {
        String query = "UPDATE AdminID SET Пароль = ?, ФИО = ?, Должность = ?, Статус = ?, Зарплата = ?, КодВалюты = ?, НомерГруппы = ? WHERE Логин = ?";

        String status = adminID.getPassword();
        System.out.println("-----");
        System.out.println(status);


        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {


            // Устанавливаем параметры запроса для обновления
            preparedStatement.setString(1, adminID.getPassword()); // Пароль
            preparedStatement.setString(2, adminID.getFio()); // ФИО
            preparedStatement.setString(3, adminID.getRole()); // Должность
            preparedStatement.setString(4, adminID.getStatus()); // Статус

            BigDecimal salary = BigDecimal.valueOf(adminID.getSalary()); // Преобразуем зарплату в BigDecimal
            preparedStatement.setBigDecimal(5, salary); // Зарплата

            preparedStatement.setString(6, adminID.getCurrency()); // Код валюты
            preparedStatement.setString(7, adminID.getGroups()); // Номер группы
            preparedStatement.setString(8, adminID.getLogin()); // Логин для поиска

            // Выполняем запрос на обновление
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Возвращаем true, если хотя бы одна строка была обновлена
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Возвращаем false, если произошла ошибка
        }
    }
}

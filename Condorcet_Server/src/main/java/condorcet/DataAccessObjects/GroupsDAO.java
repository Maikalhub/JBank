package condorcet.DataAccessObjects;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import condorcet.Models.Entities.Groups;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupsDAO
{

    // Метод для получения всех групп
    public List<Groups> findAll()
    {
        List<Groups> groupsList = new ArrayList<>();
        String query = "SELECT * FROM Groups";  // Запрос для выборки всех групп

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query))
        {

            // Обрабатываем результаты выборки
            while (resultSet.next()) {
                Groups group = new Groups(
                        resultSet.getInt("ID"),// ID группы
                        resultSet.getString("Номергруппы"),  // Номер группы
                        resultSet.getString("ФИО"),           // ФИО
                        resultSet.getString("Руководитель")            // Руководитель
                );
                groupsList.add(group);  // Добавляем группу в список
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupsList;  // Возвращаем список всех групп
    }

    // Method to find groups by 'Rykovoditel'
    public List<Groups> findByRykovoditel(String rykovoditel)
    {
        List<Groups> groupsList = new ArrayList<>();
        String query = "SELECT * FROM Groups WHERE Руководитель = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, rykovoditel);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Groups group = new Groups(
                        resultSet.getInt("ID"),
                        resultSet.getString("ФИО"),
                        resultSet.getString("Номергруппы"),
                        resultSet.getString("Руководитель")
                );
                groupsList.add(group); // Add group to list
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupsList;  // Return list of groups
    }

    // Method to find groups by 'Rykovoditel'
    public List<Groups> findBynumbergroups(String number_groups)
    {
        List<Groups> groupsList = new ArrayList<>();
        String query = "SELECT * FROM Groups WHERE НомерГруппы = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, number_groups);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Groups group = new Groups(
                        resultSet.getInt("ID"),
                        resultSet.getString("ФИО"),
                        resultSet.getString("Номергруппы"),
                        resultSet.getString("Руководитель")
                );
                groupsList.add(group); // Add group to list
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupsList;  // Return list of groups
    }










    // Метод для удаления группы по ФИО
    public boolean deleteGroupByFIO(String fio) {
        String query = "DELETE FROM Groups WHERE ФИО = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Устанавливаем параметр ФИО в запрос
            preparedStatement.setString(1, fio);

            // Выполняем запрос на удаление
            int rowsAffected = preparedStatement.executeUpdate();

            // Если строки были удалены, возвращаем true
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();  // Выводим ошибку, если произошла
            return false;  // Если произошла ошибка, возвращаем false
        }
    }


    public boolean updateuseringroupsbyfio(Groups newUser2) {
        String updateQuery = "UPDATE Groups SET НомерГруппы = ?, Руководитель = ? WHERE ФИО = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            // Устанавливаем параметры для обновления данных
            //preparedStatement.setString(1, newUser2.getGroup_number());
            preparedStatement.setString(1, newUser2.getGroup_number());
            preparedStatement.setString(2, newUser2.getFIO_());
            preparedStatement.setString(3, newUser2.getRyk());
            preparedStatement.setString(4, newUser2.getRyk());

            // Выполняем запрос на обновление
            int rowsAffected = preparedStatement.executeUpdate();

            // Если были обновлены строки, возвращаем true
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();  // Выводим ошибку, если произошла
            return false;  // Если произошла ошибка, возвращаем false
        }
    }


    public boolean addNewClientToGroup(Groups newGroup)
    {

        String checkQuery = "SELECT COUNT(*) FROM Groups WHERE ФИО = ?";
        String insertQuery = "INSERT INTO Groups (НомерГруппы, ФИО, Руководитель) VALUES (?, ?, ?)";

        // Сначала проверим, существует ли уже такой клиент в группе
        try (Connection connection = DBConnection.getConnection()) {

            // Проверка наличия записи с таким ФИО
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setString(1, newGroup.getFIO_());

                try (ResultSet rs = checkStatement.executeQuery())
                {
                    if (rs.next()) {
                        int count = rs.getInt(1); // Получаем количество записей с таким ФИО
                        if (count > 0) {
                            // Если запись уже существует, возвращаем false или выводим сообщение
                            System.out.println("Пользователь с таким ФИО уже существует в базе.");
                            return false; // Можно вернуть false, если не разрешено повторное добавление
                        }
                    }
                }
            }

            // Если проверка прошла, то выполняем вставку в таблицу
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                // Устанавливаем параметры для запроса
                preparedStatement.setString(1, newGroup.getGroup_number());
                preparedStatement.setString(2, newGroup.getFIO_());
                preparedStatement.setString(3, newGroup.getRyk());

                // Выполняем запрос на вставку данных в базу
                int rowsAffected = preparedStatement.executeUpdate();

                // Если запись была добавлена успешно, возвращаем true
                if (rowsAffected > 0) {
                    return true;
                } else {
                    // Вставка не удалась
                    System.out.println("Не удалось добавить клиента в группу.");
                    return false;
                }

            }

            } catch (SQLException e) {
                // Общая обработка других ошибок SQL
                System.err.println("Ошибка при работе с БД: " + e.getMessage());
                return false; // Если произошла ошибка при подключении или выполнении запроса
            }

        }

    public boolean synchronizeGroupsWithAdminID() {
        String checkAdminIDQuery = "SELECT Логин FROM AdminID WHERE ФИО = ?";
        String updateGroupsQuery = "UPDATE Groups SET Руководитель = ? WHERE ФИО = ? AND НомерГруппы = ?";

        try (Connection connection = DBConnection.getConnection()) {
            // Для каждой записи в таблице Groups
            for (Groups group : findAll()) {
                // Проверяем, есть ли запись с таким ФИО в таблице AdminID
                try (PreparedStatement checkStatement = connection.prepareStatement(checkAdminIDQuery)) {
                    checkStatement.setString(1, group.getRyk());
                    try (ResultSet resultSet = checkStatement.executeQuery()) {
                        if (resultSet.next()) {
                            // Если запись есть, обновляем Руководитель в таблице Groups
                            try (PreparedStatement updateStatement = connection.prepareStatement(updateGroupsQuery)) {
                                updateStatement.setString(1, resultSet.getString("Логин"));
                                updateStatement.setString(2, group.getFIO_());
                                updateStatement.setString(3, group.getGroup_number());
                                updateStatement.executeUpdate();
                            }
                        } else {
                            // Если записи нет, можно вывести сообщение об ошибке или пропустить обновление
                            System.out.println("Руководитель с ФИО " + group.getRyk() + " не найден в таблице AdminID.");
                        }
                    }
                }
            }
            return true; // Синхронизация выполнена успешно
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Произошла ошибка при синхронизации
        }
    }
}


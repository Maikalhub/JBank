package condorcet.DataAccessObjects;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import condorcet.Models.Entities.Archive;
import condorcet.Models.Entities.Works;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArchiveDAO
{

    public List<Archive> findByRykovoditel(String rykovoditel)
    {
        List<Archive> archiveList = new ArrayList<>();
        String query = "SELECT * FROM Archive WHERE Руководитель = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, rykovoditel);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Archive archive = new Archive(
                        resultSet.getInt("ID"),
                        resultSet.getString("ФИО"),
                        resultSet.getString("Номергруппы"),
                        resultSet.getString("Руководитель"),
                        resultSet.getString("Работа"),
                        resultSet.getString("СтатусРаботы"),
                        resultSet.getString("ДатаПриема"),
                        resultSet.getString("ДатаПроверки")
                );
                archiveList.add(archive); // Add group to list
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return archiveList;  // Return list of groups
    }

    public List<Archive> findByfio(String fio)
    {
        List<Archive> archiveList = new ArrayList<>();
        String query = "SELECT * FROM Archive WHERE ФИО = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, fio);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Archive archive = new Archive(
                        resultSet.getInt("ID"),
                        resultSet.getString("ФИО"),
                        resultSet.getString("Номергруппы"),
                        resultSet.getString("Руководитель"),
                        resultSet.getString("Работа"),
                        resultSet.getString("СтатусРаботы"),
                        resultSet.getString("ДатаПриема"),
                        resultSet.getString("ДатаПроверки")
                );
                archiveList.add(archive); // Add group to list
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return archiveList;  // Return list of groups
    }


    // Метод для удаления группы по ФИО
    public boolean deleteWorksByFIO(String fio) {
        String query = "DELETE FROM Archive WHERE ФИО = ?";

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


    public boolean addNewClientToWork(Archive newGroup)
    {
        System.out.println("-----------------++++++++++++++++++++++++++--------------------------");
        // Запрос для проверки наличия записи с таким ФИО в таблице Groups

        // Запрос для вставки новой записи в таблицу Works
        String insertQuery = "INSERT INTO Archive (ФИО, НомерГруппы, Руководитель, Работа, СтатусРаботы, ДатаПриема, ДатаПроверки ) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection())
        {

            // Если проверка прошла, то выполняем вставку в таблицу Works
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                // Устанавливаем параметры для запроса
                preparedStatement.setString(1, newGroup.getTask_FIO());
                preparedStatement.setString(2, newGroup.getNumber_groups());
                preparedStatement.setString(3, newGroup.getRyk_());
                preparedStatement.setString(4, newGroup.getWorks());
                preparedStatement.setString(5, newGroup.getStatus_works());
                preparedStatement.setString(6, newGroup.getAccepted_time());
                preparedStatement.setString(7, newGroup.getCheck_time());

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

            } catch (SQLServerException e) {
                // Обрабатываем ошибку SQL, например, нарушение внешнего ключа
                System.err.println("Ошибка SQL: " + e.getMessage());
                return false; // Если ошибка базы данных, возвращаем false

            } catch (SQLException e) {
                // Общая обработка других ошибок SQL
                System.err.println("Ошибка при работе с БД: " + e.getMessage());
                return false; // Если произошла ошибка при подключении или выполнении запроса
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при подключении к базе данных: " + e.getMessage());
            return false;
        }
    }





}


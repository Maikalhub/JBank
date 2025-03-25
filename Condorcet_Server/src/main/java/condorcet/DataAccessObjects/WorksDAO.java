package condorcet.DataAccessObjects;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import condorcet.Models.Entities.Works;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorksDAO
{

        // Метод для добавления нового задания
        public boolean addWork(Works work) {
            String query = "INSERT INTO Works (task_FIO, number_groups, ryk_, works, status_works, accepted_time, check_time) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, work.getTask_FIO());
                preparedStatement.setString(2, work.getNumber_groups());
                preparedStatement.setString(3, work.getRyk_());
                preparedStatement.setString(4, work.getWorks());
                preparedStatement.setString(5, work.getStatus_works());
                preparedStatement.setString(6, work.getAccepted_time());
                preparedStatement.setString(7, work.getCheck_time());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        // Метод для получения всех заданий
        public List<Works> findAll() {
            List<Works> worksList = new ArrayList<>();
            String query = "SELECT * FROM Works";

            try (Connection connection = DBConnection.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                while (resultSet.next()) {
                    Works work = new Works(
                            resultSet.getInt("task_Id"),
                            resultSet.getString("task_FIO"),
                            resultSet.getString("number_groups"),
                            resultSet.getString("ryk_"),
                            resultSet.getString("works"),
                            resultSet.getString("status_works"),
                            resultSet.getString("accepted_time"),
                            resultSet.getString("check_time")
                    );
                    worksList.add(work);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return worksList;
        }

        // Метод для поиска задания по ID
        public Works findById(int taskId) {
            String query = "SELECT * FROM Works WHERE task_Id = ?";
            Works work = null;

            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setInt(1, taskId);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    work = new Works(
                            resultSet.getInt("task_Id"),
                            resultSet.getString("task_FIO"),
                            resultSet.getString("number_groups"),
                            resultSet.getString("ryk_"),
                            resultSet.getString("works"),
                            resultSet.getString("status_works"),
                            resultSet.getString("accepted_time"),
                            resultSet.getString("check_time")
                    );
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return work;
        }

        // Метод для обновления задания
        public boolean updateWork(Works work)
        {
            String query = "UPDATE Works SET task_FIO = ?, number_groups = ?, ryk_ = ?, works = ?, status_works = ?, accepted_time = ?, check_time = ? WHERE task_Id = ?";

            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query))
            {

                preparedStatement.setString(1, work.getTask_FIO());
                preparedStatement.setString(2, work.getNumber_groups());
                preparedStatement.setString(3, work.getRyk_());
                preparedStatement.setString(4, work.getWorks());
                preparedStatement.setString(5, work.getStatus_works());
                preparedStatement.setString(6, work.getAccepted_time());
                preparedStatement.setString(7, work.getCheck_time());
                preparedStatement.setInt(8, work.getTask_Id());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return false;
        }

        // Метод для удаления задания
        public boolean deleteWork(int taskId)
        {
            String query = "DELETE FROM Works WHERE task_Id = ?";

            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setInt(1, taskId);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return false;
        }

    public List<Works> findByRykovoditel(String rykovoditel)
    {
        List<Works> groupsList = new ArrayList<>();
        String query = "SELECT * FROM Works WHERE Руководитель = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, rykovoditel);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Works work = new Works(
                        resultSet.getInt("ID"),
                        resultSet.getString("ФИО"),
                        resultSet.getString("Номергруппы"),
                        resultSet.getString("Руководитель"),
                        resultSet.getString("Работа"),
                        resultSet.getString("СтатусРаботы"),
                        resultSet.getString("ДатаПриема"),
                        resultSet.getString("ДатаПроверки")
                        );
                groupsList.add(work); // Add group to list
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupsList;  // Return list of groups
    }


    public List<Works> findByRykovoditel2(String fio)
    {
        List<Works> groupsList = new ArrayList<>();
        String query = "SELECT * FROM Works WHERE ФИО = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, fio);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Works work = new Works(
                        resultSet.getInt("ID"),
                        resultSet.getString("ФИО"),
                        resultSet.getString("Номергруппы"),
                        resultSet.getString("Руководитель"),
                        resultSet.getString("Работа"),
                        resultSet.getString("СтатусРаботы"),
                        resultSet.getString("ДатаПриема"),
                        resultSet.getString("ДатаПроверки")
                );
                groupsList.add(work); // Add group to list
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupsList;  // Return list of groups
    }








    // Метод для удаления группы по ФИО
    public boolean deleteWorksByFIO(String fio) {
        String query = "DELETE FROM Works WHERE ФИО = ?";

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


    public boolean addNewClientToWork(Works newGroup) {
        // Запрос для проверки наличия записи с таким ФИО в таблице Groups
        String checkQuery = "SELECT COUNT(*) FROM Groups WHERE ФИО = ?";

        // Запрос для вставки новой записи в таблицу Works
        String insertQuery = "INSERT INTO Works (ФИО, НомерГруппы, Руководитель, Работа, СтатусРаботы, ДатаПриема, ДатаПроверки ) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection()) {

            // Сначала проверим, существует ли уже такое ФИО в таблице Groups
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setString(1, newGroup.getTask_FIO());

                try (ResultSet rs = checkStatement.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1); // Получаем количество записей с таким ФИО
                        if (count == 0) {
                            // Если запись не найдена в таблице Groups, возвращаем false
                            System.out.println("Пользователь с таким ФИО не найден в группе.");
                            return false; // Запрещаем вставку в таблицу Works
                        }
                    }
                }
            }

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


    public boolean updateuseringroupsbyfio(Works newWork) {
        // SQL-запрос для обновления данных
        String updateQuery = "UPDATE Works SET Работа = ?, СтатусРаботы = ?, ДатаПриема = ?, ДатаПроверки = ? WHERE ФИО = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            // Устанавливаем параметры для запроса
            preparedStatement.setString(1, newWork.getWorks());              // Устанавливаем значение для работы
            preparedStatement.setString(2, newWork.getStatus_works());       // Устанавливаем статус работы
            preparedStatement.setString(3, newWork.getAccepted_time());      // Устанавливаем дату приема
            preparedStatement.setString(4, newWork.getCheck_time());         // Устанавливаем дату проверки
            preparedStatement.setString(5, newWork.getTask_FIO());           // Устанавливаем ФИО для фильтрации

            // Выполняем запрос на обновление данных
            int rowsAffected = preparedStatement.executeUpdate();

            // Если были обновлены строки, возвращаем true
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();  // Выводим ошибку, если произошла
            return false;  // Если произошла ошибка, возвращаем false
        }
    }


}


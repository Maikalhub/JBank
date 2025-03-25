package main.Models.Entities;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;
import main.Data.*;
import main.Enums.RequestType;
import main.Enums.ResponseStatus;
import main.JDBC.JDBC;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Models.TCP.Request;
import main.Models.TCP.Response;
import main.Utility.ClientSocket;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;


public class Admin {
    /// Начальная инициализация данных
    private Client client;  // Ссылка на клиент
    private String username;
    private String password;
    private Stage primaryStage;// Ссылка на основной Stage
    // Кнопки Main //
    /// Мои данные
    @FXML
    private Button mydata;
    /// Активность
    @FXML
    private Button datagroups;
    @FXML
    private Button tasks;
    @FXML
    private Button report;
    @FXML
    private Button archive;
    /// Настройки
    @FXML
    private Button update;
    @FXML
    private Button quetion;
    @FXML
    private Button exit;
    // Сцены //
    @FXML
    /// Сцена "mainpane"
    private AnchorPane mainpane;
    /// Сцена "mydatapane"
    @FXML
    private AnchorPane mydatapane;
    @FXML
    private AnchorPane mydatapane1;
    @FXML
    private Button mydatapane1_addscene;
    @FXML
    private Button mydatapane1_editscene;
    @FXML
    private Text mydatapane1_fio;
    @FXML
    private TextField mydatapane1_login;
    @FXML
    private TextField mydatapane1_numbergroups;
    @FXML
    private TextField mydatapane1_password;
    @FXML
    private TextField mydatapane1_post;
    @FXML
    private TextField mydatapane1_salary;
    @FXML
    private TextField mydatapane1_status;
    @FXML
    private AnchorPane mydatapane2;
    @FXML
    private TextField mydatapane2_fio;
    @FXML
    private Button mydatapane2_clear;
    @FXML
    private Button mydatapane2_confirm;
    @FXML
    private TextField mydatapane2_login;
    @FXML
    private TextField mydatapane2_numbergroups;
    @FXML
    private TextField mydatapane2_password;
    @FXML
    private TextField mydatapane2_post;
    @FXML
    private TextField mydatapane2_salary;
    @FXML
    private TextField mydatapane2_status;
    @FXML
    /// Сцена "datagroupspane"
    private AnchorPane datagroupspane;
    @FXML
    private AnchorPane datagroupspane1;
    @FXML
    private Button datagroupspane1_add;
    @FXML
    private Button datagroupspane1_delete;
    @FXML
    private AnchorPane datagroupspane2;
    @FXML
    private Button datagroupspane2_clear;
    @FXML
    private Button datagroupspane2_confirm;
    @FXML
    private TextField datagroupspane2_fio;
    @FXML
    private TextField datagroupspane2_numbergroups;
    @FXML
    private TextField datagroupspane2_ryk;
    @FXML
    private AnchorPane datagroupspane3;
    @FXML
    private Button datagroupspane3_clear;
    @FXML
    private Button datagroupspane3_confirm;
    @FXML
    private TextField datagroupspane3_login;
    @FXML
    private TextField datagroupspane3_numbergroups;
    @FXML
    private TextField datagroupspane3_password;
    @FXML
    private TextField datagroupspane3_post;
    @FXML
    private TextField datagroupspane3_salary;
    @FXML
    private TextField datagroupspane3_status;
    @FXML
    private TextField datagroupspane3_fio;
    @FXML
    private TextField datagroupspane3_ryk;
    /// Сцена "taskspane"
    @FXML
    private AnchorPane taskspane;
    @FXML
    private AnchorPane taskspane1;
    /// ///
    @FXML
    private Button taskspane1_archive;
    @FXML
    private Button taskspane1_add;
    @FXML
    private Button taskspane1_delete;
    @FXML
    private Button taskspane1_edit;
    @FXML
    private AnchorPane taskspane2;
    @FXML
    private TextField taskspane2_checktime;
    @FXML
    private TextField taskspane2_acceptedtime;
    @FXML
    private Button taskspane2_clear;
    @FXML
    private Button taskspane2_confirm;
    @FXML
    private TextField taskspane2_fio;
    @FXML
    private TextField taskspane2_numbergroups;
    @FXML
    private TextField taskspane2_ryk;
    @FXML
    private TextField taskspane2_statuswork;
    @FXML
    private TextField taskspane2_work;
    @FXML
    private AnchorPane taskspane3;
    @FXML
    private TextField taskspane3_acceptedtime;
    @FXML
    private TextField taskspane3_checktime;
    @FXML
    private Button taskspane3_clear;
    @FXML
    private Button taskspane3_confirm;
    @FXML
    private TextField taskspane3_fio;
    @FXML
    private TextField taskspane3_numbergroups;
    @FXML
    private TextField taskspane3_ryk;
    @FXML
    private TextField taskspane3_statuswork;
    @FXML
    private TextField taskspane3_work;
    /// Сцена "reportpane"
    @FXML
    private AnchorPane reportpane;
    @FXML
    private Button reportpane_add;
    @FXML
    private ChoiceBox<?> reportpane_choosebox;
    @FXML
    private Button reportpane_clear;
    @FXML
    private TextField reportpane_fio;
    /// Сцена "acrhivepane"
    @FXML
    private AnchorPane archivepane;
    @FXML
    private Button archivepane_add;
    @FXML
    private Button archivepane_back;
    @FXML
    private Button archivepane_delete;
    // Сцены //
    //
    @FXML
    private Text fio1;
    @FXML
    private Text fio3;
    @FXML
    private Text fio31;
    //
    private ObservableList<AdminA> adminaList = FXCollections.observableArrayList();
    private ObservableList<UserA> useraList = FXCollections.observableArrayList();
    private ObservableList<AdminID> adminidList = FXCollections.observableArrayList();
    private ObservableList<UserID> useridList = FXCollections.observableArrayList();
    //
    // Таблицы //
    /// Таблица "Данные группы" Сцена "Группа"
    @FXML
    private TableView<Groups> datagroupspane_table;
    @FXML
    private TableColumn<Groups, Integer> idColumn;
    @FXML
    private TableColumn<Groups, String> fioColumn;
    @FXML
    private TableColumn<Groups, String> groupNumberColumn;
    @FXML
    private TableColumn<Groups, String> rykColumn;
    //
    private ObservableList<Groups> groupsList = FXCollections.observableArrayList();
    //
    /// Таблица "Задачи" Сцена "Задачи"
    @FXML
    private TableView<Works> taskspane_table;
    @FXML
    private TableColumn<Works, Integer> id_column_tasks;
    @FXML
    private TableColumn<Works, String> FIO_column_tasks;
    @FXML
    private TableColumn<Works, String> number_groups_column_tasks;
    @FXML
    private TableColumn<Works, String> ryk_column_tasks;
    @FXML
    private TableColumn<Works, String> work_column_tasks;
    @FXML
    private TableColumn<Works, String> status_column_tasks;
    @FXML
    private TableColumn<Works, Byte> file_column_tasks;
    @FXML
    private TableColumn<Works, String> time_file_column_tasks;
    //
    private ObservableList<Works> worksList = FXCollections.observableArrayList();
    //
    /// Таблица "Архив" Сцена "Архив"
    @FXML
    private TableView<Archive> archivepane_table;
    @FXML
    private TableColumn<Archive, Integer> archive_id;
    @FXML
    private TableColumn<Archive, String> archive_FIO;
    @FXML
    private TableColumn<Archive, String> archive_number_group;
    @FXML
    private TableColumn<Archive, String> archive_ryk;
    @FXML
    private TableColumn<Archive, String> archive_work;
    @FXML
    private TableColumn<Archive, String> archive_status;
    @FXML
    private TableColumn<Archive, String> archive_accepted;
    @FXML
    private TableColumn<Archive, String> archive_check;
    //
    private ObservableList<Archive> archiveList = FXCollections.observableArrayList();
    //
    // Таблицы //
    // ChooseBox //
    /// ChooseBox    Сцена "Отчеты"
    @FXML
    private ChoiceBox<String> report_report_choosebox;
    // ChooseBox //

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /// Получение данных после авторизации

    public void admin_get_login_and_password(String username, String password) throws SQLException {
        this.username = username;
        this.password = password;

        Client client = new Client();

        client.setLogin(username);
        client.setPassword(password);

        System.out.println("------------");
        System.out.println(client.getLogin());
        System.out.println(client.getPassword());
        System.out.println("------------");

        // Можно обновить UI или другие действия, если нужно
        // Устанавливаем логин и пароль в поля на UI
        System.out.println("Данные получены");
        System.out.println(username);
        System.out.println(password);
        // Устанавливаем логин и пароль в поля на UI
        System.out.println("Устанавливаем значения логина и пароля в сцене");
        mydatapane1_login.setText(username);
        mydatapane1_password.setText(password);
        System.out.println("Конец");
        System.out.println("Данные переданы для добавления в бд");
        JDBC.connect();
        update_system_all_data(username, password);
        JDBC.close();

    }

    private void update_system_all_data(String login, String password) throws SQLException {
        String query = "SELECT * FROM AdminID WHERE Логин = ?";
        try (PreparedStatement stmt = JDBC.connection.prepareStatement(query)) {
            stmt.setString(1, login);

            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next())
                {
                    // Извлекаем данные из ResultSet
                    String Login = rs.getString("Логин");
                    String Password = rs.getString("Пароль");
                    String FullName = rs.getString("ФИО");
                    String Dol = rs.getString("Должность");
                    String Status = rs.getString("Статус");
                    String Salary = rs.getString("Зарплата");
                    String Numergroups = rs.getString("НомерГруппы");

                    System.out.println(Login);
                    System.out.println(Password);
                    System.out.println(FullName);
                    System.out.println(Dol);
                    System.out.println(Status);
                    System.out.println(Salary);
                    System.out.println(Numergroups);

                    //mydatapane1_login.setText("");
                    mydatapane1_password.setText(Password);
                    // Устанавливаем данные в элементы интерфейса
                    mydatapane1_fio.setText(FullName);
                    mydatapane1_post.setText(Dol);
                    mydatapane1_status.setText(Status);
                    mydatapane1_salary.setText(Salary);
                    mydatapane1_numbergroups.setText(Numergroups);

                    update_groups(FullName, "Руководитель");
                    update_works(FullName, "Руководитель");
                    update_archive(FullName, "Руководитель");
                    // Загружаем данные о группах после получения информации о пользователе
                    datagroupspane3_ryk.setText(FullName);
                } else {
                    // Если логин не найден, можно отобразить сообщение
                    showErrorMessage("Пользователь с таким логином не найден.");
                }
            }
        } catch (SQLException e) {
            // Обработка исключений и логирование ошибок
            showErrorMessage("Ошибка при получении данных: " + e.getMessage());
            e.printStackTrace();  // Можно заменить на логирование
        }
    }


    public void update_groups(String login, String password) throws SQLException {

        // Создаем объект Client с логином и паролем
        Client client = new Client(login, password);

        // Логируем логин и пароль
        System.out.println("Логин: " + client.getLogin());
        System.out.println("Пароль: " + client.getPassword());

        // Создаем запрос и сериализуем клиента в JSON
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(client)); // Сериализация клиента
        requestModel.setRequestType(RequestType.SELECT_GROUP); // Устанавливаем тип запроса как SELECT_GROUP

        // Отправляем запрос на сервер
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getOut() != null) {
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            System.out.println("Запрос отправлен: " + new Gson().toJson(requestModel));
        } else {
            System.out.println("Ошибка: сокет не инициализирован.");
            return;
        }

        // Получаем ответ от сервера
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getInStream() != null) {
            String serverResponse = null;
            try {
                serverResponse = ClientSocket.getInstance().getInStream().readLine();
            } catch (IOException e) {
                System.err.println("Ошибка при получении ответа от сервера: " + e.getMessage());
                return;
            }

            // Получаем ответ от сервера
            if (serverResponse != null) {
                // Выводим ответ от сервера
                System.out.println("Ответ от сервера: " + serverResponse);

                // Преобразуем ответ в объект Response
                Response responseModel = new Gson().fromJson(serverResponse, Response.class);

                // Если ответ успешный, десериализуем массив объектов Groups
                if (responseModel.getResponseStatus() == ResponseStatus.OK) {
                    try {
                        JsonArray responseDataArray = JsonParser.parseString(responseModel.getResponseData()).getAsJsonArray();
                        List<Groups> groupsListFromServer = new Gson().fromJson(responseDataArray, new TypeToken<List<Groups>>() {
                        }.getType());

                        // Очищаем старые данные и добавляем новые
                        groupsList.clear();
                        groupsList.addAll(groupsListFromServer);

                        // После обновления данных в списке, TableView автоматически отобразит изменения
                        System.out.println("Данные групп успешно обновлены в таблице.");

                    } catch (Exception e) {
                        System.err.println("Ошибка при десериализации данных: " + e.getMessage());
                    }
                } else {
                    System.out.println("Ошибка при получении данных от сервера: " + responseModel.getResponseMessage());
                }
            }
        }
    }

    public void update_works(String login, String password) throws SQLException {

        // Создаем объект Client с логином и паролем
        Client client = new Client(login, password);

        // Логируем логин и пароль
        System.out.println("Логин: " + client.getLogin());
        System.out.println("Пароль: " + client.getPassword());

        // Создаем запрос и сериализуем клиента в JSON
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(client)); // Сериализация клиента
        requestModel.setRequestType(RequestType.SELECT_WORKS); // Устанавливаем тип запроса как SELECT_GROUP

        // Отправляем запрос на сервер
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getOut() != null) {
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            System.out.println("Запрос отправлен: " + new Gson().toJson(requestModel));
        } else {
            System.out.println("Ошибка: сокет не инициализирован.");
            return;
        }

        // Получаем ответ от сервера
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getInStream() != null) {
            String serverResponse = null;
            try {
                serverResponse = ClientSocket.getInstance().getInStream().readLine();
            } catch (IOException e) {
                System.err.println("Ошибка при получении ответа от сервера: " + e.getMessage());
                return;
            }

            // Получаем ответ от сервера
            if (serverResponse != null) {
                // Выводим ответ от сервера
                System.out.println("Ответ от сервера: " + serverResponse);

                // Преобразуем ответ в объект Response
                Response responseModel = new Gson().fromJson(serverResponse, Response.class);

                // Если ответ успешный, десериализуем массив объектов Groups
                if (responseModel.getResponseStatus() == ResponseStatus.OK) {
                    try {
                        JsonArray responseDataArray = JsonParser.parseString(responseModel.getResponseData()).getAsJsonArray();
                        List<Works> worksListFromServer = new Gson().fromJson(responseDataArray, new TypeToken<List<Works>>() {
                        }.getType());

                        // Очищаем старые данные и добавляем новые
                        worksList.clear();
                        worksList.addAll(worksListFromServer);

                        // После обновления данных в списке, TableView автоматически отобразит изменения
                        System.out.println("Данные групп успешно обновлены в таблице.");

                    } catch (Exception e) {
                        System.err.println("Ошибка при десериализации данных: " + e.getMessage());
                    }
                } else {
                    System.out.println("Ошибка при получении данных от сервера: " + responseModel.getResponseMessage());
                }
            }
        }
    }

    public void update_archive(String login, String password) throws SQLException {

        // Создаем объект Client с логином и паролем
        Client client = new Client(login, password);

        // Логируем логин и пароль
        System.out.println("Логин: " + client.getLogin());
        System.out.println("Пароль: " + client.getPassword());

        // Создаем запрос и сериализуем клиента в JSON
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(client)); // Сериализация клиента
        requestModel.setRequestType(RequestType.SELECT_ARCHIVE); // Устанавливаем тип запроса как SELECT_GROUP

        // Отправляем запрос на сервер
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getOut() != null) {
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            System.out.println("Запрос отправлен: " + new Gson().toJson(requestModel));
        } else {
            System.out.println("Ошибка: сокет не инициализирован.");
            return;
        }

        // Получаем ответ от сервера
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getInStream() != null) {
            String serverResponse = null;
            try {
                serverResponse = ClientSocket.getInstance().getInStream().readLine();
            } catch (IOException e) {
                System.err.println("Ошибка при получении ответа от сервера: " + e.getMessage());
                return;
            }

            // Получаем ответ от сервера
            if (serverResponse != null) {
                // Выводим ответ от сервера
                System.out.println("Ответ от сервера: " + serverResponse);

                // Преобразуем ответ в объект Response
                Response responseModel = new Gson().fromJson(serverResponse, Response.class);

                // Если ответ успешный, десериализуем массив объектов Groups
                if (responseModel.getResponseStatus() == ResponseStatus.OK) {
                    try {
                        JsonArray responseDataArray = JsonParser.parseString(responseModel.getResponseData()).getAsJsonArray();
                        List<Archive> archiveListFromServer = new Gson().fromJson(responseDataArray, new TypeToken<List<Archive>>() {
                        }.getType());

                        // Очищаем старые данные и добавляем новые
                        archiveList.clear();
                        archiveList.addAll(archiveListFromServer);

                        // После обновления данных в списке, TableView автоматически отобразит изменения
                        System.out.println("Данные групп успешно обновлены в таблице.");

                    } catch (Exception e) {
                        System.err.println("Ошибка при десериализации данных: " + e.getMessage());
                    }
                } else {
                    System.out.println("Ошибка при получении данных от сервера: " + responseModel.getResponseMessage());
                }
            }
        }
    }


    // Методы Main //
    @FXML
    public void update(ActionEvent actionEvent)
    {
        //
        clearAdminMyData();
        clearTableAllData();
        //
        try {
            admin_get_login_and_password(username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Database authorization");
        alert.setHeaderText(null);
        alert.setContentText("Данные успешно обновлены");
        alert.showAndWait();
        //
    }

    @FXML
    public void quetion(ActionEvent actionEvent) {

    }

    @FXML
    public void exit(ActionEvent actionEvent) {
        //
        clearAdminMyData();
        // Закрыть текущую сцену
        Stage currentStage = (Stage) exit.getScene().getWindow(); // Change this to any existing button (like exit_button)
        currentStage.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Back to menu");
        alert.setHeaderText(null);
        alert.setContentText("Вы вернулись в меню");
        alert.showAndWait();

        URL clientFXML = getClass().getResource("/client.fxml");
        if (clientFXML == null) {
            System.out.println("Файл client.fxml не найден");
        } else {
            FXMLLoader loader = new FXMLLoader(clientFXML);
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
    }

    // Методы Main //
    // Инициализвация данных //
    @FXML
    public void initialize() {
        // Настройка столбцов
        idColumn.setCellValueFactory(new PropertyValueFactory<>("group_Id"));
        fioColumn.setCellValueFactory(new PropertyValueFactory<>("FIO_"));
        groupNumberColumn.setCellValueFactory(new PropertyValueFactory<>("group_number"));
        rykColumn.setCellValueFactory(new PropertyValueFactory<>("ryk"));
        // Устанавливаем ObservableList в TableView
        datagroupspane_table.setItems(groupsList);


        //
        // Task ID Column (update to use correct property name)
        id_column_tasks.setCellValueFactory(new PropertyValueFactory<>("task_Id"));
        // FIO Column
        FIO_column_tasks.setCellValueFactory(new PropertyValueFactory<>("task_FIO"));
        // Number Groups Column
        number_groups_column_tasks.setCellValueFactory(new PropertyValueFactory<>("number_groups"));
        // Supervisor (Ryk) Column
        ryk_column_tasks.setCellValueFactory(new PropertyValueFactory<>("ryk_"));
        // Works Column (task description)
        work_column_tasks.setCellValueFactory(new PropertyValueFactory<>("works"));
        // Status Column
        status_column_tasks.setCellValueFactory(new PropertyValueFactory<>("status_works"));
        // File Column (if you're displaying it somehow)
        file_column_tasks.setCellValueFactory(new PropertyValueFactory<>("file"));
        // Time File Column
        time_file_column_tasks.setCellValueFactory(new PropertyValueFactory<>("time_file"));
        //

        taskspane_table.setItems(worksList);


        //
        // Task ID Column (update to use correct property name)
        archive_id.setCellValueFactory(new PropertyValueFactory<>("task_Id"));
        // FIO Column
        archive_FIO.setCellValueFactory(new PropertyValueFactory<>("task_FIO"));
        // Number Groups Column
        archive_number_group.setCellValueFactory(new PropertyValueFactory<>("number_groups"));
        // Supervisor (Ryk) Column
        archive_ryk.setCellValueFactory(new PropertyValueFactory<>("ryk_"));
        // Works Column (task description)
        archive_work.setCellValueFactory(new PropertyValueFactory<>("works"));
        // Status Column
        archive_status.setCellValueFactory(new PropertyValueFactory<>("status_works"));
        // File Column (if you're displaying it somehow)
        archive_accepted.setCellValueFactory(new PropertyValueFactory<>("accepted_time"));
        // Time File Column
        archive_check.setCellValueFactory(new PropertyValueFactory<>("check_time"));

        archivepane_table.setItems(archiveList);


        // Инициализация ChooseBox //
        /// ChooseBox "Выбор типа отчета" Сцены "Отчет"//
        // Устанавливаем начальное сообщение

        // Проверка, чтобы избежать NullPointerException
        if (report_report_choosebox != null) {
            // Добавление элементов
            report_report_choosebox.getItems().add("Выберите вид отчета");
            report_report_choosebox.getItems().add("AreaChart");
            report_report_choosebox.getItems().add("BarChart");
            report_report_choosebox.getItems().add("BubbleChart");
            report_report_choosebox.getItems().add("LineChart");
            report_report_choosebox.getItems().add("PineChart");

            // Установка начального значения
            report_report_choosebox.setValue("Выберите вид отчета");

            // Слушатель на выбор элемента
            report_report_choosebox.setOnAction(event -> {
                String selected = report_report_choosebox.getValue();
                System.out.println("Выбран отчет: " + selected);
            });
        } else {
            System.err.println("report_report_choosebox не был инициализирован");
        }



        // Инициализация ChooseBox //
    }

    // Инициализация данных //
    // Отображение сцен //
    @FXML
    public void mydata_scene(ActionEvent actionEvent) {
        mydatapane.setVisible(true);
        mydatapane1.setVisible(true);
        mydatapane2.setVisible(false);
        datagroupspane.setVisible(false);
        datagroupspane1.setVisible(false);
        datagroupspane2.setVisible(false);
        datagroupspane3.setVisible(false);
        taskspane.setVisible(false);
        taskspane1.setVisible(false);
        taskspane2.setVisible(false);
        taskspane3.setVisible(false);
        reportpane.setVisible(false);
        archivepane.setVisible(false);
    }

    @FXML
    public void groups_scene(ActionEvent actionEvent) {
        mydatapane.setVisible(false);
        mydatapane1.setVisible(false);
        mydatapane2.setVisible(false);
        datagroupspane.setVisible(true);
        datagroupspane1.setVisible(true);
        datagroupspane2.setVisible(false);
        datagroupspane3.setVisible(false);
        taskspane.setVisible(false);
        taskspane1.setVisible(false);
        taskspane2.setVisible(false);
        taskspane3.setVisible(false);
        reportpane.setVisible(false);
        archivepane.setVisible(false);
    }

    @FXML
    public void tasks_scene(ActionEvent actionEvent) {
        mydatapane.setVisible(false);
        mydatapane1.setVisible(false);
        mydatapane2.setVisible(false);
        datagroupspane.setVisible(false);
        datagroupspane1.setVisible(false);
        datagroupspane2.setVisible(false);
        datagroupspane3.setVisible(false);
        taskspane.setVisible(true);
        taskspane1.setVisible(true);
        taskspane2.setVisible(false);
        taskspane3.setVisible(false);
        reportpane.setVisible(false);
        archivepane.setVisible(false);
    }

    @FXML
    public void report_scene(ActionEvent actionEvent) {
        mydatapane.setVisible(false);
        mydatapane1.setVisible(false);
        mydatapane2.setVisible(false);
        datagroupspane.setVisible(false);
        datagroupspane1.setVisible(false);
        datagroupspane2.setVisible(false);
        datagroupspane3.setVisible(false);
        taskspane.setVisible(false);
        taskspane1.setVisible(false);
        taskspane2.setVisible(false);
        taskspane3.setVisible(false);
        reportpane.setVisible(true);
        archivepane.setVisible(false);
    }

    @FXML
    public void archive_scene(ActionEvent actionEvent) {
        mydatapane.setVisible(false);
        mydatapane1.setVisible(false);
        mydatapane2.setVisible(false);
        datagroupspane.setVisible(false);
        datagroupspane1.setVisible(false);
        datagroupspane2.setVisible(false);
        datagroupspane3.setVisible(false);
        taskspane.setVisible(false);
        reportpane.setVisible(false);
        taskspane1.setVisible(false);
        taskspane2.setVisible(false);
        taskspane3.setVisible(false);
        archivepane.setVisible(true);
    }

    // Отображение сцен //
    // Отображение //
    private void clearAdminMyData() {
        // Очистим все поля, связанные с пользователем
        mydatapane1_login.setText("");
        mydatapane1_password.setText("");
        mydatapane1_fio.setText("");
        mydatapane1_post.setText("");
        mydatapane1_salary.setText("");
        mydatapane1_status.setText("");
        mydatapane1_numbergroups.setText("");


    }

    private void clearAllData() {
        // Очистим все поля, связанные с пользователем
        mydatapane1_login.setText("");
        mydatapane1_password.setText("");
        mydatapane1_fio.setText("");
        mydatapane1_post.setText("");
        mydatapane1_salary.setText("");
        mydatapane1_status.setText("");
        mydatapane1_numbergroups.setText("");

        // Можно также сбросить другие поля или данные, если они хранятся в других объектах
        // Например:
        //my_data_table.getItems().clear();
        //tasks_table.getItems().clear();
        //archive_table.getItems().clear();

    }

    private void clearTableAllData() {

        // Можно также сбросить другие поля или данные, если они хранятся в других объектах
        //Например:
        datagroupspane_table.getItems().clear();
        taskspane_table.getItems().clear();
        archivepane_table.getItems().clear();

    }


    public void update_from_authorization(String username, String password) {
        this.username = username;
        this.password = password;

    }

    /// Метод для отображения ошибок (пример)
    private void showErrorMessage(String message) {
        // Здесь вы можете вывести сообщение в Alert или логи
        System.out.println("Ошибка: " + message);
    }

    // Отображение сообщений //
    // Геттеры и Сеттеры //
    public Client getClient() {
        return client;
    }

    // Геттеры, если нужно
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    // Метод для установки ссылки на клиента
    public void setClient(Client client) {
        this.client = client;
    }
    // Геттеры и Сеттеры //
    // Функции кнопок //


    @FXML
    void mydatapane1_addscene_function(ActionEvent event)
    {

    }

    @FXML
    void mydatapane1_editscene_function(ActionEvent event)
    {
        mydatapane2_login.setText(mydatapane1_login.getText());
        mydatapane2_password.setText(mydatapane1_password.getText());
        mydatapane2_fio.setText(mydatapane1_fio.getText());
        mydatapane2_post.setText(mydatapane1_post.getText());
        mydatapane2_salary.setText(mydatapane1_salary.getText());
        mydatapane2_status.setText(mydatapane1_status.getText());
        mydatapane2_numbergroups.setText(mydatapane1_numbergroups.getText());

        System.out.println("--------------------------");
        System.out.println(mydatapane2_login.getText());
        System.out.println(mydatapane2_password.getText());
        System.out.println(mydatapane2_fio.getText());
        System.out.println(mydatapane2_post.getText());
        System.out.println(mydatapane2_salary.getText());
        System.out.println(mydatapane2_status.getText());
        System.out.println(mydatapane2_numbergroups.getText());
        System.out.println(mydatapane2_post.getText());
        System.out.println(mydatapane2_salary.getText());
        System.out.println("--------------------------");

        mydatapane.setVisible(true);
        mydatapane1.setVisible(false);
        mydatapane2.setVisible(true);
        datagroupspane.setVisible(false);
        datagroupspane1.setVisible(false);
        datagroupspane2.setVisible(false);
        datagroupspane3.setVisible(false);
        taskspane.setVisible(false);
        taskspane1.setVisible(false);
        taskspane2.setVisible(false);
        taskspane3.setVisible(false);
        reportpane.setVisible(false);
        archivepane.setVisible(false);
    }

    @FXML
    void mydatapane2_clear_fuction(ActionEvent event)
    {
        //mydatapane2_login.setText("");
        //mydatapane2_password.setText("");
        mydatapane2_fio.setText("");
        mydatapane2_post.setText("");
        mydatapane2_salary.setText("");
        mydatapane2_status.setText("");
        mydatapane2_numbergroups.setText("");

        //
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Database authorization");
        alert.setHeaderText(null);
        alert.setContentText("Данные успешно очищены");
        alert.showAndWait();
        //
    }

    @FXML
    void mydatapane2_confirm_function(ActionEvent event)
    {
        // Проверка, что все поля заполнены
        if (mydatapane2_login.getText().isEmpty() ||
                mydatapane2_password.getText().isEmpty() ||
                mydatapane2_fio.getText().isEmpty() ||
                mydatapane2_post.getText().isEmpty() ||
                mydatapane2_salary.getText().isEmpty() ||
                mydatapane2_status.getText().isEmpty() ||
                mydatapane2_numbergroups.getText().isEmpty()) {

            // Если хотя бы одно поле пустое, показываем предупреждение
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Database authorization");
            alert.setHeaderText(null);
            alert.setContentText("Пожалуйста, заполните все поля.");
            alert.showAndWait();
        }
        else
        {
            // Получаем данные из полей ввода
            String fio_mydata =  mydatapane2_fio.getText();
            String login_mydata = mydatapane2_login.getText();
            String password_mydata = mydatapane2_password.getText();
            String numbergroups_mydata = mydatapane2_numbergroups.getText();
            String post_mydata = mydatapane2_post.getText();
            String status_mydata = mydatapane2_status.getText();
            String salary_mydata = String.valueOf(mydatapane2_salary.getText());


            // Если все поля заполнены, выводим данные в консоль
            System.out.println("FIO: " + fio_mydata);
            System.out.println("Login: " + login_mydata);
            System.out.println("Password: " + password_mydata);
            System.out.println("Number of group: " + numbergroups_mydata);
            System.out.println("Post: " + post_mydata);
            System.out.println("Status: " + status_mydata);
            System.out.println("Salary: " + salary_mydata);

            // Если все поля заполнены, показываем информацию о подтверждении
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Database authorization");
            alert.setHeaderText(null);
            alert.setContentText("Все данные успешно введены.");
            alert.showAndWait();

            mydatapane2_confirm_function_edit_AdminID(fio_mydata,login_mydata,password_mydata,numbergroups_mydata,post_mydata,status_mydata, Double.parseDouble(salary_mydata));


        }
    }

    void mydatapane2_confirm_function_edit_AdminID
            (
            String fio_datagroups_,
            String login_datagroups_,
            String password_datagroups_,
            String numbergroups_datagroups_,
            String post_datagroups_,
            String sdstatus_datagroups_,
            double salary_datagroups_
    ) {
        System.out.println("dfsdgd");
        // Выводим данные, которые были введены
        System.out.println(fio_datagroups_);
        System.out.println(login_datagroups_);
        System.out.println(password_datagroups_);
        System.out.println(numbergroups_datagroups_);
        System.out.println(post_datagroups_);
        System.out.println(sdstatus_datagroups_);
        System.out.println(salary_datagroups_);

        String fio_datagroups__ = fio_datagroups_;
        String login_datagroups__ = login_datagroups_;
        String password_datagroups__ = password_datagroups_;
        String numbergroups_datagroups__ = numbergroups_datagroups_;
        String post_datagroups__ = post_datagroups_;
        String sdstatus_datagroups__ = sdstatus_datagroups_;
        Double salary_datagroups__ = salary_datagroups_;




        // Создаем новый объект UserID с переданными значениями
        AdminID newAdmin = new AdminID(0 ,login_datagroups__, password_datagroups__, fio_datagroups__, post_datagroups__, sdstatus_datagroups__,salary_datagroups__, "BYR",  numbergroups_datagroups__);
       System.out.println("-+__+_+_+_+_++_");
        System.out.println(newAdmin.getFio());

        // Создаем запрос и сериализуем объект в JSON
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(newAdmin)); // Сериализация объекта UserID
        requestModel.setRequestType(RequestType.EDIT_ADMINID); // Устанавливаем тип запроса как ADD_USERID

        // Отправляем запрос на сервер
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getOut() != null) {
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel)); // Отправляем запрос
            ClientSocket.getInstance().getOut().flush(); // Очищаем поток
            System.out.println("Запрос отправлен: " + new Gson().toJson(requestModel));
        } else {
            System.out.println("Ошибка: сокет не инициализирован.");
            return;
        }

        // Получаем ответ от сервера
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getInStream() != null) {
            String serverResponse = null;
            try {
                serverResponse = ClientSocket.getInstance().getInStream().readLine(); // Чтение ответа от сервера
            } catch (IOException e) {
                System.err.println("Ошибка при получении ответа от сервера: " + e.getMessage());
                return;
            }

            // Обработка ответа от сервера
            if (serverResponse != null) {
                System.out.println("Ответ от сервера: " + serverResponse);

                // Преобразуем ответ в объект Response
                Response responseModel = new Gson().fromJson(serverResponse, Response.class);

                // Если статус OK, показываем успех
                if (responseModel.getResponseStatus() == ResponseStatus.OK) {
                    try {
                        // После успешного добавления данных, обновляем TableView
                        UserID addedUser = new Gson().fromJson(responseModel.getResponseMessage(), UserID.class);
                        System.out.println(addedUser.toString());
                        // Предположим, у вас есть TableView, куда нужно добавить новый элемент
                        // Например, если TableView хранит список UserID:
                        // userTableView.getItems().add(addedUser); // Добавляем новый объект в таблицу

                        System.out.println("Данные успешно изменены в таблице пользователей.");
                    } catch (Exception e) {
                        System.err.println("Ошибка при десериализации данных: " + e.getMessage());
                    }
                } else {
                    System.out.println("Ошибка при изменении данных: " + responseModel.getResponseMessage());
                }
            }
        }
    }




    @FXML
    void datagroupspane1_delete_function(ActionEvent event)
    {
        // Получаем выбранную строку из TableView
        Groups selectedWork = datagroupspane_table.getSelectionModel().getSelectedItem();
        // Создаем объект Client с логином и паролем
        // Создаем запрос и сериализуем клиента в JSON
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(selectedWork)); // Сериализация клиента
        requestModel.setRequestType(RequestType.DELETE_GROUP); // Устанавливаем тип запроса как SELECT_GROUP

        // Отправляем запрос на сервер
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getOut() != null) {
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            System.out.println("Запрос отправлен: " + new Gson().toJson(requestModel));
        } else {
            System.out.println("Ошибка: сокет не инициализирован.");
            return;
        }
        // Получаем ответ от сервера
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getInStream() != null) {
            String serverResponse = null;
            try {
                serverResponse = ClientSocket.getInstance().getInStream().readLine();
            } catch (IOException e) {
                System.err.println("Ошибка при получении ответа от сервера: " + e.getMessage());
                return;
            }
            // Получаем ответ от сервера
            if (serverResponse != null) {
                // Выводим ответ от сервера
                System.out.println("Ответ от сервера: " + serverResponse);

                // Преобразуем ответ в объект Response
                Response responseModel = new Gson().fromJson(serverResponse, Response.class);

                // Если ответ успешный, десериализуем массив объектов Groups
                if (responseModel.getResponseStatus() == ResponseStatus.OK) {
                    try
                    {
                        // После обновления данных в списке, TableView автоматически отобразит изменения
                        System.out.println("Данные успешно удалены в таблице.");

                    } catch (Exception e) {
                        System.err.println("Ошибка при десериализации данных: " + e.getMessage());
                    }
                } else
                {
                    System.out.println("Ошибка при удалении данных от сервера: " + responseModel.getResponseMessage());
                }
            }
        }



    }



    @FXML
    void datagrouppane1_add_function(ActionEvent event)
    {
        mydatapane.setVisible(true);
        mydatapane1.setVisible(false);
        mydatapane2.setVisible(false);
        datagroupspane.setVisible(true);
        datagroupspane1.setVisible(false);
        datagroupspane2.setVisible(false);
        datagroupspane3.setVisible(true);
        taskspane.setVisible(false);
        taskspane1.setVisible(false);
        taskspane2.setVisible(false);
        taskspane3.setVisible(false);
        reportpane.setVisible(false);
        archivepane.setVisible(false);
    }

    public void datagroupspane1_edit_function(ActionEvent actionEvent)
    {
        Groups selectedWork = datagroupspane_table.getSelectionModel().getSelectedItem();
        // Проверяем, что элемент выбран
        String fullname = selectedWork.getFIO_();
        String numbergroup = selectedWork.getGroup_number();
        String Ryk = selectedWork.getRyk();
        System.out.println("fullname: " + fullname);
        System.out.println("numbergroup: " + numbergroup);
        System.out.println("Ryk: " + Ryk);

        datagroupspane2_fio.setText(fullname);
        datagroupspane2_numbergroups.setText(numbergroup);
        datagroupspane2_ryk.setText(Ryk);


        mydatapane.setVisible(true);
        mydatapane1.setVisible(false);
        mydatapane2.setVisible(false);
        datagroupspane.setVisible(true);
        datagroupspane1.setVisible(false);
        datagroupspane2.setVisible(true);
        datagroupspane3.setVisible(false);
        taskspane.setVisible(false);
        taskspane1.setVisible(false);
        taskspane2.setVisible(false);
        taskspane3.setVisible(false);
        reportpane.setVisible(false);
        archivepane.setVisible(false);


    }


    @FXML
    void datagroupspane2_clear_function(ActionEvent event)
    {
        clearDatagroupspane2();

    }

    private void clearDatagroupspane2()
    {
        datagroupspane2_numbergroups.setText("");
        datagroupspane2_ryk.setText("");
        //
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Database authorization");
        alert.setHeaderText(null);
        alert.setContentText("Данные успешно очищены");
        alert.showAndWait();
        //
    }

    private void clearALLDatagroupspane2()
    {
        datagroupspane2_fio.setText("");
        datagroupspane2_numbergroups.setText("");
        datagroupspane2_ryk.setText("");

    }


    @FXML
    void datagroupspane2_confirm_function(ActionEvent event)
    {
        // Получаем текст из текстовых полей
        String fio = datagroupspane2_fio.getText();
        String numbergroups = datagroupspane2_numbergroups.getText();
        String ryk = datagroupspane2_ryk.getText();

        // Проверяем, что все поля заполнены
        if (fio.isEmpty() || numbergroups.isEmpty() || ryk.isEmpty()) {
            // Показываем сообщение об ошибке, если хотя бы одно поле пустое
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Пожалуйста, заполните все поля.");
            alert.setContentText("Необходимо ввести ФИО, номер группы и РПК.");
            alert.showAndWait();
        } else {
            // Здесь код, если все поля заполнены
            System.out.println("Все поля заполнены:");
            System.out.println("ФИО: " + fio);
            System.out.println("Номер группы: " + numbergroups);
            System.out.println("РПК: " + ryk);
        }

        /// Сначала надо обновить данные в таблице UserID потом в Groups ///
        //datagrouppane2_confirm_function_data_1(fio, numbergroups);
        datagrouppane2_confirm_function_data_2(fio, numbergroups, ryk);

    }




    @FXML
    void datagroupspane3_clear_function(ActionEvent event)
    {
        datagroupspane3_fio.clear();
        datagroupspane3_login.clear();
        datagroupspane3_password.clear();
        datagroupspane3_numbergroups.clear();
        datagroupspane3_post.clear();
        datagroupspane3_status.clear();
        datagroupspane3_salary.clear();

        //
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Database authorization");
        alert.setHeaderText(null);
        alert.setContentText("Данные успешно очищены");
        alert.showAndWait();
        //
    }
    /// 

    @FXML
    void datagroupspane3_confirm_function(ActionEvent event) throws SQLException {
        // Получаем данные из полей ввода
        String ryk = datagroupspane3_ryk.getText();
        String fio_datagroups = datagroupspane3_fio.getText();
        String login_datagroups = datagroupspane3_login.getText();
        String password_datagroups = datagroupspane3_password.getText();
        String numbergroups_datagroups = datagroupspane3_numbergroups.getText();
        String post_datagroups = datagroupspane3_post.getText();
        String sdstatus_datagroups = datagroupspane3_status.getText();
        Double salary_datagroups = Double.valueOf(datagroupspane3_salary.getText());

        // Проверка на пустые значения
        if (ryk.isEmpty()) {
            System.out.println("Поле 'Руководитель' не может быть пустым!");
            return;  // Прерываем выполнение метода, если поле пустое
        }

        if (fio_datagroups.isEmpty()) {
            System.out.println("Поле 'ФИО' не может быть пустым!");
            return;  // Прерываем выполнение метода, если поле пустое
        }

        if (login_datagroups.isEmpty()) {
            System.out.println("Поле 'Логин' не может быть пустым!");
            return;  // Прерываем выполнение метода, если поле пустое
        }

        if (password_datagroups.isEmpty()) {
            System.out.println("Поле 'Пароль' не может быть пустым!");
            return;  // Прерываем выполнение метода, если поле пустое
        }

        if (numbergroups_datagroups.isEmpty()) {
            System.out.println("Поле 'Номер группы' не может быть пустым!");
            return;  // Прерываем выполнение метода, если поле пустое
        }

        if (post_datagroups.isEmpty()) {
            System.out.println("Поле 'Должность' не может быть пустым!");
            return;  // Прерываем выполнение метода, если поле пустое
        }

        if (sdstatus_datagroups.isEmpty()) {
            System.out.println("Поле 'Статус' не может быть пустым!");
            return;  // Прерываем выполнение метода, если поле пустое
        }

        if (salary_datagroups.isNaN()) {
            System.out.println("Поле 'Зарплата' не может быть пустым!");
            return;  // Прерываем выполнение метода, если поле пустое
        }

        // Дополнительная проверка для зарплаты на корректность ввода (чтобы это было число)
        try {
            Double salary = Double.valueOf(salary_datagroups);
        } catch (NumberFormatException e) {
            System.out.println("Поле 'Зарплата' должно быть числом!");
            return;  // Прерываем выполнение метода, если зарплата не является числом
        }

        System.out.println("Логин: " + login_datagroups);
        System.out.println("Пароль: " + password_datagroups);
        System.out.println("ФИО: " + fio_datagroups);
        System.out.println("Должность: " + post_datagroups);
        System.out.println("Статус: " + sdstatus_datagroups);
        System.out.println("Зарплата: " + salary_datagroups);
        System.out.println("Номер Группы: " + numbergroups_datagroups);

        // Дополнительная логика, например, отправка данных на сервер, если все заполнено
        datagroupspane3_add_function_UserID(fio_datagroups,login_datagroups,password_datagroups,numbergroups_datagroups,post_datagroups,sdstatus_datagroups,salary_datagroups);
        datagroupspane3_add_function_UserA(login_datagroups, password_datagroups);
        datagroupspane3_add_function_Groups(fio_datagroups,login_datagroups,ryk);
    }

    // Функции кнопок //
    void datagroupspane3_add_function_UserA(String login_, String password_)
    {
        System.out.println(login_);
        System.out.println(password_);
        //

        // Создаем объект Client с логином и паролем
        Client client = new Client(login_, password_);

        // Логируем логин и пароль
        System.out.println("Логин: " + client.getLogin());
        System.out.println("Пароль: " + client.getPassword());

        // Создаем запрос и сериализуем клиента в JSON
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(client)); // Сериализация клиента
        requestModel.setRequestType(RequestType.ADD_USERA); // Устанавливаем тип запроса как SELECT_GROUP

        // Отправляем запрос на сервер
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getOut() != null) {
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            System.out.println("Запрос отправлен: " + new Gson().toJson(requestModel));
        } else {
            System.out.println("Ошибка: сокет не инициализирован.");
            return;
        }
        // Получаем ответ от сервера
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getInStream() != null) {
            String serverResponse = null;
            try {
                serverResponse = ClientSocket.getInstance().getInStream().readLine();
            } catch (IOException e) {
                System.err.println("Ошибка при получении ответа от сервера: " + e.getMessage());
                return;
            }
            // Получаем ответ от сервера
            if (serverResponse != null) {
                // Выводим ответ от сервера
                System.out.println("Ответ от сервера: " + serverResponse);

                // Преобразуем ответ в объект Response
                Response responseModel = new Gson().fromJson(serverResponse, Response.class);

                // Если ответ успешный, десериализуем массив объектов Groups
                if (responseModel.getResponseStatus() == ResponseStatus.OK) {
                    try
                    {
                        // После обновления данных в списке, TableView автоматически отобразит изменения
                        System.out.println("Данные успешно добавлены в таблице авторизации данных .");

                    } catch (Exception e) {
                        System.err.println("Ошибка при десериализации данных: " + e.getMessage());
                    }
                } else
                {
                    System.out.println("Ошибка при добавлении данных от сервера: " + responseModel.getResponseMessage());
                }
            }
        }


    }


    void datagroupspane3_add_function_UserID(
            String fio_datagroups_,
            String login_datagroups_,
            String password_datagroups_,
            String numbergroups_datagroups_,
            String post_datagroups_,
            String sdstatus_datagroups_,
            Double salary_datagroups_)
    {
        // Выводим данные, которые были введены
        System.out.println(login_datagroups_);
        System.out.println(password_datagroups_);
        System.out.println(fio_datagroups_);
        System.out.println(post_datagroups_);
        System.out.println(sdstatus_datagroups_);
        System.out.println(salary_datagroups_);
        System.out.println(numbergroups_datagroups_);

        // Создаем новый объект UserID с переданными значениями
        UserID newUser = new UserID(0, login_datagroups_, password_datagroups_, fio_datagroups_, post_datagroups_, sdstatus_datagroups_, "BYR", salary_datagroups_, numbergroups_datagroups_);

        // Создаем запрос и сериализуем объект в JSON
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(newUser)); // Сериализация объекта UserID
        requestModel.setRequestType(RequestType.ADD_USERID); // Устанавливаем тип запроса как ADD_USERID

        // Отправляем запрос на сервер
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getOut() != null) {
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel)); // Отправляем запрос
            ClientSocket.getInstance().getOut().flush(); // Очищаем поток
            System.out.println("Запрос отправлен: " + new Gson().toJson(requestModel));
        } else {
            System.out.println("Ошибка: сокет не инициализирован.");
            return;
        }

        // Получаем ответ от сервера
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getInStream() != null) {
            String serverResponse = null;
            try {
                serverResponse = ClientSocket.getInstance().getInStream().readLine(); // Чтение ответа от сервера
            } catch (IOException e) {
                System.err.println("Ошибка при получении ответа от сервера: " + e.getMessage());
                return;
            }

            // Обработка ответа от сервера
            if (serverResponse != null) {
                System.out.println("Ответ от сервера: " + serverResponse);

                // Преобразуем ответ в объект Response
                Response responseModel = new Gson().fromJson(serverResponse, Response.class);

                // Если статус OK, показываем успех
                if (responseModel.getResponseStatus() == ResponseStatus.OK) {
                    try {
                        // После успешного добавления данных, обновляем TableView
                        UserID addedUser = new Gson().fromJson(responseModel.getResponseMessage(), UserID.class);
                        System.out.println(addedUser.toString());
                        // Предположим, у вас есть TableView, куда нужно добавить новый элемент
                        // Например, если TableView хранит список UserID:
                        // userTableView.getItems().add(addedUser); // Добавляем новый объект в таблицу

                        System.out.println("Данные успешно добавлены в таблицу пользователей.");
                    } catch (Exception e) {
                        System.err.println("Ошибка при десериализации данных: " + e.getMessage());
                    }
                } else {
                    System.out.println("Ошибка при добавлении данных: " + responseModel.getResponseMessage());
                }
            }
        }
    }

    void datagroupspane3_add_function_Groups(String fio_datagroups_, String numbergroups_datagroups_, String ryk)
    {
        // Выводим данные, которые были введены
        System.out.println(fio_datagroups_);
        System.out.println(numbergroups_datagroups_);
        System.out.println(ryk);


        // Создаем новый объект UserID с переданными значениями
        Groups newUser = new Groups(0, fio_datagroups_, numbergroups_datagroups_,ryk);

        // Создаем запрос и сериализуем объект в JSON
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(newUser)); // Сериализация объекта UserID
        requestModel.setRequestType(RequestType.ADD_GROUP); // Устанавливаем тип запроса как ADD_USERID

        // Отправляем запрос на сервер
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getOut() != null) {
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel)); // Отправляем запрос
            ClientSocket.getInstance().getOut().flush(); // Очищаем поток
            System.out.println("Запрос отправлен: " + new Gson().toJson(requestModel));
        } else {
            System.out.println("Ошибка: сокет не инициализирован.");
            return;
        }

        // Получаем ответ от сервера
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getInStream() != null) {
            String serverResponse = null;
            try {
                serverResponse = ClientSocket.getInstance().getInStream().readLine(); // Чтение ответа от сервера
            } catch (IOException e) {
                System.err.println("Ошибка при получении ответа от сервера: " + e.getMessage());
                return;
            }

            // Обработка ответа от сервера
            if (serverResponse != null) {
                System.out.println("Ответ от сервера: " + serverResponse);

                // Преобразуем ответ в объект Response
                Response responseModel = new Gson().fromJson(serverResponse, Response.class);

                // Если статус OK, показываем успех
                if (responseModel.getResponseStatus() == ResponseStatus.OK) {
                    try {
                        // После успешного добавления данных, обновляем TableView
                        UserID addedUser = new Gson().fromJson(responseModel.getResponseMessage(), UserID.class);
                        System.out.println(addedUser.toString());
                        // Предположим, у вас есть TableView, куда нужно добавить новый элемент
                        // Например, если TableView хранит список UserID:
                        // userTableView.getItems().add(addedUser); // Добавляем новый объект в таблицу

                        System.out.println("Данные успешно добавлены в таблицу пользователей.");
                    } catch (Exception e) {
                        System.err.println("Ошибка при десериализации данных: " + e.getMessage());
                    }
                } else {
                    System.out.println("Ошибка при добавлении данных: " + responseModel.getResponseMessage());
                }
            }
        }
    }


    

    // Функции кнопок //


    // Функции кнопок //
    void datagrouppane2_confirm_function_data_1(String fio_, String numbergroups_) {
        // Логируем входные данные
        System.out.println("ФИО: " + fio_);
        System.out.println("Номер группы: " + numbergroups_);

        // Создаем объект Groups с переданными параметрами
        UserID groups = new UserID(0," ", " ",fio_," "," " , " ",0.0, numbergroups_);

        // Создаем запрос и сериализуем клиента в JSON
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(groups));  // Сериализация клиента
        requestModel.setRequestType(RequestType.EDIT_USERID2); // Устанавливаем тип запроса как EDIT_GROUP

        // Отправляем запрос на сервер
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getOut() != null) {
            String jsonRequest = new Gson().toJson(requestModel);
            ClientSocket.getInstance().getOut().println(jsonRequest);
            ClientSocket.getInstance().getOut().flush();
            System.out.println("Запрос отправлен: " + jsonRequest);
        } else {
            System.err.println("Ошибка: сокет не инициализирован.");
            return;
        }

        // Получаем ответ от сервера
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getInStream() != null) {
            try {
                String serverResponse = ClientSocket.getInstance().getInStream().readLine();

                if (serverResponse != null) {
                    // Выводим ответ от сервера
                    System.out.println("Ответ от сервера: " + serverResponse);

                    // Преобразуем ответ в объект Response
                    Response responseModel = new Gson().fromJson(serverResponse, Response.class);

                    // Обрабатываем ответ
                    if (responseModel.getResponseStatus() == ResponseStatus.OK) {
                        System.out.println("Данные успешно обновлены в таблице UserID на сервере.");
                        // Обновляем таблицу, если операция прошла успешно
                        updateTableView();
                    } else {
                        System.out.println("Ошибка при обновлении данных на сервере: " + responseModel.getResponseMessage());
                    }
                }
            } catch (IOException e) {
                System.err.println("Ошибка при получении ответа от сервера: " + e.getMessage());
            }
        } else {
            System.err.println("Ошибка: поток ввода не инициализирован.");
        }
    }

    void datagrouppane2_confirm_function_data_2(String fio_, String numbergroups_, String ryk_) {
        // Логируем входные данные
        System.out.println("ФИО: " + fio_);
        System.out.println("Номер группы: " + numbergroups_);
        System.out.println("Руководитель: " + ryk_);

        // Создаем объект Groups с переданными параметрами
        Groups groups = new Groups(0, fio_, numbergroups_, ryk_);



        // Создаем запрос и сериализуем клиента в JSON
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(groups));  // Сериализация клиента
        requestModel.setRequestType(RequestType.EDIT_GROUP); // Устанавливаем тип запроса как EDIT_GROUP

        // Отправляем запрос на сервер
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getOut() != null)
        {
            String jsonRequest = new Gson().toJson(requestModel);
            ClientSocket.getInstance().getOut().println(jsonRequest);
            ClientSocket.getInstance().getOut().flush();
            System.out.println("Запрос отправлен: " + jsonRequest);
        } else {
            System.err.println("Ошибка: сокет не инициализирован.");
            return;
        }

        // Получаем ответ от сервера
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getInStream() != null) {
            try {
                String serverResponse = ClientSocket.getInstance().getInStream().readLine();

                if (serverResponse != null) {
                    // Выводим ответ от сервера
                    System.out.println("Ответ от сервера: " + serverResponse);

                    // Преобразуем ответ в объект Response
                    Response responseModel = new Gson().fromJson(serverResponse, Response.class);

                    // Обрабатываем ответ
                    if (responseModel.getResponseStatus() == ResponseStatus.OK) {
                        System.out.println("Данные успешно обновлены в таблице Groups на сервере.");
                        // Обновляем таблицу, если операция прошла успешно
                        updateTableView();
                    } else {
                        System.out.println("Ошибка при обновлении данных на сервере: " + responseModel.getResponseMessage());
                    }
                }
            } catch (IOException e) {
                System.err.println("Ошибка при получении ответа от сервера: " + e.getMessage());
            }
        } else {
            System.err.println("Ошибка: поток ввода не инициализирован.");
        }
    }

    // Метод для обновления TableView после успешного ответа от сервера
    private void updateTableView() {
        // Обновление данных в TableView автоматически произойдет, если данные в коллекции изменятся
        // Пример:
        System.out.println("Обновление данных в таблице авторизации...");
        // Если используется ObservableList, достаточно просто обновить коллекцию
        // tableView.setItems(observableList);
    }

    @FXML
    void taskspane1_archive_function(ActionEvent event)
    {
        // Получаем выбранную строку из TableView
        Works selectedWork = taskspane_table.getSelectionModel().getSelectedItem();
        // Проверяем, что элемент выбран
        String fullname = selectedWork.getTask_FIO();
        String numbergroup = selectedWork.getNumber_groups();
        String ryk = selectedWork.getRyk_();
        String work = selectedWork.getWorks();
        String status_work = selectedWork.getStatus_works();
        String accepted_time = selectedWork.getAccepted_time();
        String check_time = selectedWork.getCheck_time();

        System.out.println(fullname);
        System.out.println(numbergroup);
        System.out.println(ryk);
        System.out.println(work);
        System.out.println(status_work);
        System.out.println(accepted_time);
        System.out.println(check_time);


        // Создаем объект Client с логином и паролем
        // Создаем запрос и сериализуем клиента в JSON
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(selectedWork)); // Сериализация клиента
        requestModel.setRequestType(RequestType.ADD_ARCHIVE); // Устанавливаем тип запроса как SELECT_GROUP

        // Отправляем запрос на сервер
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getOut() != null) {
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            System.out.println("Запрос отправлен: " + new Gson().toJson(requestModel));
        } else {
            System.out.println("Ошибка: сокет не инициализирован.");
            return;
        }
        // Получаем ответ от сервера
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getInStream() != null) {
            String serverResponse = null;
            try {
                serverResponse = ClientSocket.getInstance().getInStream().readLine();
            } catch (IOException e) {
                System.err.println("Ошибка при получении ответа от сервера: " + e.getMessage());
                return;
            }
            // Получаем ответ от сервера
            if (serverResponse != null) {
                // Выводим ответ от сервера
                System.out.println("Ответ от сервера: " + serverResponse);

                // Преобразуем ответ в объект Response
                Response responseModel = new Gson().fromJson(serverResponse, Response.class);

                // Если ответ успешный, десериализуем массив объектов Groups
                if (responseModel.getResponseStatus() == ResponseStatus.OK) {
                    try
                    {
                        // После обновления данных в списке, TableView автоматически отобразит изменения
                        System.out.println("Данные успешно добавлены в архив.");
                        //taskspane1_archive_function(selectedWork);

                    } catch (Exception e) {
                        System.err.println("Ошибка при десериализации данных: " + e.getMessage());
                    }
                } else
                {
                    System.out.println("Ошибка при удалении данных от сервера: " + responseModel.getResponseMessage());
                }
            }
        }
    }

    void taskspane1_archive_function(Works works)
    {
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(works)); // Сериализация клиента
        requestModel.setRequestType(RequestType.DELETE_WORKS); // Устанавливаем тип запроса как SELECT_GROUP

        // Отправляем запрос на сервер
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getOut() != null) {
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            System.out.println("Запрос отправлен: " + new Gson().toJson(requestModel));
        } else {
            System.out.println("Ошибка: сокет не инициализирован.");
            return;
        }
        // Получаем ответ от сервера
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getInStream() != null) {
            String serverResponse = null;
            try {
                serverResponse = ClientSocket.getInstance().getInStream().readLine();
            } catch (IOException e) {
                System.err.println("Ошибка при получении ответа от сервера: " + e.getMessage());
                return;
            }
            // Получаем ответ от сервера
            if (serverResponse != null) {
                // Выводим ответ от сервера
                System.out.println("Ответ от сервера: " + serverResponse);

                // Преобразуем ответ в объект Response
                Response responseModel = new Gson().fromJson(serverResponse, Response.class);

                // Если ответ успешный, десериализуем массив объектов Groups
                if (responseModel.getResponseStatus() == ResponseStatus.OK) {
                    try
                    {
                        // После обновления данных в списке, TableView автоматически отобразит изменения
                        System.out.println("Данные успешно удалены в таблице.");

                    } catch (Exception e) {
                        System.err.println("Ошибка при десериализации данных: " + e.getMessage());
                    }
                } else
                {
                    System.out.println("Ошибка при удалении данных от сервера: " + responseModel.getResponseMessage());
                }
            }
        }
    }





    @FXML
    void taskspane1_delete_function(ActionEvent event)
    {
        // Получаем выбранную строку из TableView
        Works selectedWork = taskspane_table.getSelectionModel().getSelectedItem();
        // Создаем объект Client с логином и паролем
        // Создаем запрос и сериализуем клиента в JSON
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(selectedWork)); // Сериализация клиента
        requestModel.setRequestType(RequestType.DELETE_WORKS); // Устанавливаем тип запроса как SELECT_GROUP

        // Отправляем запрос на сервер
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getOut() != null) {
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            System.out.println("Запрос отправлен: " + new Gson().toJson(requestModel));
        } else {
            System.out.println("Ошибка: сокет не инициализирован.");
            return;
        }
        // Получаем ответ от сервера
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getInStream() != null) {
            String serverResponse = null;
            try {
                serverResponse = ClientSocket.getInstance().getInStream().readLine();
            } catch (IOException e) {
                System.err.println("Ошибка при получении ответа от сервера: " + e.getMessage());
                return;
            }
            // Получаем ответ от сервера
            if (serverResponse != null) {
                // Выводим ответ от сервера
                System.out.println("Ответ от сервера: " + serverResponse);

                // Преобразуем ответ в объект Response
                Response responseModel = new Gson().fromJson(serverResponse, Response.class);

                // Если ответ успешный, десериализуем массив объектов Groups
                if (responseModel.getResponseStatus() == ResponseStatus.OK) {
                    try
                    {
                        // После обновления данных в списке, TableView автоматически отобразит изменения
                        System.out.println("Данные успешно удалены в таблице.");

                    } catch (Exception e) {
                        System.err.println("Ошибка при десериализации данных: " + e.getMessage());
                    }
                } else
                {
                    System.out.println("Ошибка при удалении данных от сервера: " + responseModel.getResponseMessage());
                }
            }
        }
    }
    @FXML
    void taskpane1_add_function(ActionEvent event)
    {
        mydatapane.setVisible(false);
        mydatapane1.setVisible(false);
        mydatapane2.setVisible(false);
        datagroupspane.setVisible(false);
        datagroupspane1.setVisible(false);
        datagroupspane2.setVisible(false);
        datagroupspane3.setVisible(false);
        taskspane.setVisible(true);
        taskspane1.setVisible(false);
        taskspane2.setVisible(false);
        taskspane3.setVisible(true);
        reportpane.setVisible(false);
        archivepane.setVisible(false);
    }
    @FXML
    void taskpane1_edit_function(ActionEvent event)
    {
        Works selectedWork = taskspane_table.getSelectionModel().getSelectedItem();
        // Проверяем, что элемент выбран
        String fullname = selectedWork.getTask_FIO();
        String numbergroup = selectedWork.getNumber_groups();
        String ryk = selectedWork.getRyk_();
        String work = selectedWork.getWorks();
        String status_work = selectedWork.getStatus_works();
        String accepted_time = selectedWork.getAccepted_time();
        String check_time = selectedWork.getCheck_time();
        taskspane2_fio.setText(fullname);
        taskspane2_numbergroups.setText(numbergroup);
        taskspane2_ryk.setText(ryk);
        taskspane2_work.setText(work);
        taskspane2_statuswork.setText(status_work);
        taskspane2_acceptedtime.setText(accepted_time);
        taskspane2_checktime.setText(check_time);

        mydatapane.setVisible(false);
        mydatapane1.setVisible(false);
        mydatapane2.setVisible(false);
        datagroupspane.setVisible(false);
        datagroupspane1.setVisible(false);
        datagroupspane2.setVisible(false);
        datagroupspane3.setVisible(false);
        taskspane.setVisible(true);
        taskspane1.setVisible(false);
        taskspane2.setVisible(true);
        taskspane3.setVisible(false);
        reportpane.setVisible(false);
        archivepane.setVisible(false);
    }


    // edit
    public void taskpane2_confirm_function(ActionEvent actionEvent)
    {
        // Получаем данные из полей ввода
        String fio = taskspane2_fio.getText();
        String numbergroups = taskspane2_numbergroups.getText();
        String ryk = taskspane2_ryk.getText();
        String work = taskspane2_work.getText();
        String status_work = taskspane2_statuswork.getText();
        String accepted_time = taskspane2_acceptedtime.getText();
        String check_time = taskspane2_checktime.getText();

        // Проверка на пустые значения
        if (fio.isEmpty()) {
            System.out.println("Ошибка: Поле 'ФИО' не может быть пустым.");
            return; // Завершаем выполнение функции, если хотя бы одно поле пустое
        }
        if (numbergroups.isEmpty()) {
            System.out.println("Ошибка: Поле 'Номер группы' не может быть пустым.");
            return;
        }
        if (ryk.isEmpty()) {
            System.out.println("Ошибка: Поле 'Руководитель' не может быть пустым.");
            return;
        }
        if (work.isEmpty()) {
            System.out.println("Ошибка: Поле 'Работа' не может быть пустым.");
            return;
        }
        if (status_work.isEmpty()) {
            System.out.println("Ошибка: Поле 'Статус работы' не может быть пустым.");
            return;
        }
        if (accepted_time.isEmpty()) {
            System.out.println("Ошибка: Поле 'Дата приема' не может быть пустым.");
            return;
        }
        if (check_time.isEmpty()) {
            System.out.println("Ошибка: Поле 'Дата проверки' не может быть пустым.");
            return;
        }

        // Вывод значений для проверки
        System.out.println("ФИО: " + fio);
        System.out.println("Номер группы: " + numbergroups);
        System.out.println("Руководитель: " + ryk);
        System.out.println("Работа: " + work);
        System.out.println("Статус работы: " + status_work);
        System.out.println("Дата приема: " + accepted_time);
        System.out.println("Дата проверки: " + check_time);

        taskpane2_confirm_function_Groups(fio,numbergroups,ryk,work,status_work,accepted_time,check_time);
    }

    void taskpane2_confirm_function_Groups(String fio, String numbergroups, String ryk, String work, String status_work, String accepted_time, String check_time)
    {
        //
        System.out.println(fio);
        System.out.println(numbergroups);
        System.out.println(ryk);
        System.out.println(work);
        System.out.println(status_work);
        System.out.println(accepted_time);
        System.out.println(check_time);

        // Создаем объект Client с логином и паролем
        Works client = new Works(0,fio,numbergroups,ryk,work,status_work,accepted_time,check_time);



        // Создаем запрос и сериализуем клиента в JSON
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(client)); // Сериализация клиента
        requestModel.setRequestType(RequestType.EDIT_GROUP3); // Устанавливаем тип запроса как SELECT_GROUP

        // Отправляем запрос на сервер
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getOut() != null) {
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            System.out.println("Запрос отправлен: " + new Gson().toJson(requestModel));
        } else {
            System.out.println("Ошибка: сокет не инициализирован.");
            return;
        }
        // Получаем ответ от сервера
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getInStream() != null) {
            String serverResponse = null;
            try {
                serverResponse = ClientSocket.getInstance().getInStream().readLine();
            } catch (IOException e) {
                System.err.println("Ошибка при получении ответа от сервера: " + e.getMessage());
                return;
            }
            // Получаем ответ от сервера
            if (serverResponse != null) {
                // Выводим ответ от сервера
                System.out.println("Ответ от сервера: " + serverResponse);

                // Преобразуем ответ в объект Response
                Response responseModel = new Gson().fromJson(serverResponse, Response.class);

                // Если ответ успешный, десериализуем массив объектов Groups
                if (responseModel.getResponseStatus() == ResponseStatus.OK) {
                    try {
                        // После обновления данных в списке, TableView автоматически отобразит изменения
                        System.out.println("Данные успешно добавлены в таблице авторизации данных .");

                    } catch (Exception e) {
                        System.err.println("Ошибка при десериализации данных: " + e.getMessage());
                    }
                } else {
                    System.out.println("Ошибка при добавлении данных от сервера: " + responseModel.getResponseMessage());
                }
            }
        }
    }



    public void taskspane2_clear_function(ActionEvent actionEvent)
    {
        // Очищаем все поля ввода (TextField)
        taskspane2_fio.clear();
        taskspane2_numbergroups.clear();
        taskspane2_ryk.clear();
        taskspane2_work.clear();
        taskspane2_statuswork.clear();
        taskspane2_acceptedtime.clear();
        taskspane2_checktime.clear();

        // Выводим сообщение в консоль для подтверждения очистки
        System.out.println("Все поля были очищены.");
    }

    public void taskspane3_clear_function(ActionEvent actionEvent)
    {

    }

    // add
    public void taskpane3_confirm_function(ActionEvent actionEvent)
    {
        // Получаем данные из полей ввода
        String fio = taskspane3_fio.getText();
        String numbergroups = taskspane3_numbergroups.getText();
        String ryk = taskspane3_ryk.getText();
        String work = taskspane3_work.getText();
        String status_work = taskspane3_statuswork.getText();
        String accepted_time = taskspane3_acceptedtime.getText();
        String check_time = taskspane3_checktime.getText();

        //
        System.out.println(fio);
        System.out.println(numbergroups);
        System.out.println(ryk);
        System.out.println(work);
        System.out.println(status_work);
        System.out.println(accepted_time);
        System.out.println(check_time);
        //
        // Создаем объект Client с логином и паролем
        Works client = new Works(0,fio,numbergroups,ryk,work,status_work,accepted_time,check_time);

        // Создаем запрос и сериализуем клиента в JSON
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(client)); // Сериализация клиента
        requestModel.setRequestType(RequestType.ADD_WORKS); // Устанавливаем тип запроса как SELECT_GROUP

        // Отправляем запрос на сервер
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getOut() != null) {
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            System.out.println("Запрос отправлен: " + new Gson().toJson(requestModel));
        } else {
            System.out.println("Ошибка: сокет не инициализирован.");
            return;
        }
        // Получаем ответ от сервера
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getInStream() != null) {
            String serverResponse = null;
            try {
                serverResponse = ClientSocket.getInstance().getInStream().readLine();
            } catch (IOException e) {
                System.err.println("Ошибка при получении ответа от сервера: " + e.getMessage());
                return;
            }
            // Получаем ответ от сервера
            if (serverResponse != null) {
                // Выводим ответ от сервера
                System.out.println("Ответ от сервера: " + serverResponse);

                // Преобразуем ответ в объект Response
                Response responseModel = new Gson().fromJson(serverResponse, Response.class);

                // Если ответ успешный, десериализуем массив объектов Groups
                if (responseModel.getResponseStatus() == ResponseStatus.OK) {
                    try {
                        // После обновления данных в списке, TableView автоматически отобразит изменения
                        System.out.println("Данные успешно добавлены в таблице авторизации данных .");

                    } catch (Exception e) {
                        System.err.println("Ошибка при десериализации данных: " + e.getMessage());
                    }
                } else {
                    System.out.println("Ошибка при добавлении данных от сервера: " + responseModel.getResponseMessage());
                }
            }
        }
    }









    @FXML
    void reportpane_add_function(ActionEvent event) throws IOException {
       /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/diagrammes.fxml"));
        StackPane root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Получаем контроллер
        Diagram controller = loader.getController();

        // Загружаем данные и отображаем их в графике
        controller.fetchDataFromServer();

        // Настроим сцену и покажем
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Chart Example");
        primaryStage.show();
        */

    }

    @FXML
    void reportpane_clear_function(ActionEvent event) {}






    //
    @FXML
    void archivepane_add_function(ActionEvent event) {}
    @FXML
    void archivepane_back_function(ActionEvent event) {}
    @FXML
    void archivepane_delete_function(ActionEvent event) {}
    //





    // Метод для обновления TableView после успешного ответа от сервера
    private void updateTableView6() {
        // Обновление данных в TableView автоматически произойдет, если данные в коллекции изменятся
        // Пример:
        System.out.println("Обновление данных в таблице авторизации...");
        // Если используется ObservableList, достаточно просто обновить коллекцию
        // tableView.setItems(observableList);
    }

    public Groups selectWork2(Groups selectedWork)
    {

        return selectedWork;
    }
}

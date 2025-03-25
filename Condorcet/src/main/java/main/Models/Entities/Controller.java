package main.Models.Entities;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import main.Models.TCP.Response;
import main.Models.TCP.Request;
import main.Enums.RequestType;
import main.Enums.ResponseStatus;
import main.Utility.ClientSocket;

public class Controller
{

    private Client client;

    @FXML
    private PasswordField password_text;

    @FXML
    private TextField username_text;

    @FXML
    private Button authorization_connect;

    @FXML
    private Label labelMessage;

    // Устанавливаем ссылку на клиентский объект, чтобы использовать его в контроллере
    public void setClient(Client client) {
        this.client = client;
    }

    @FXML
    public void Login() throws Exception {
        // Получаем логин и пароль из UI
        String login = username_text.getText();
        String password = password_text.getText();

        // Проверка, если поля пустые
        if (login.isEmpty() || password.isEmpty()) {
            labelMessage.setText("Логин и пароль не могут быть пустыми.");
            labelMessage.setVisible(true);
            return;
        }

        // Создаем объект Client с логином и паролем
        Client client = new Client(login, password);

        // Создаем запрос и сериализуем клиента в JSON
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(client)); // Сериализация клиента
        requestModel.setRequestType(RequestType.LOGIN); // Устанавливаем тип запроса как LOGIN

        // Отправляем запрос на сервер
        if (ClientSocket.getInstance() == null) {
            labelMessage.setText("Не удалось создать подключение к серверу.");
            labelMessage.setVisible(true);
            return;
        }
        if (ClientSocket.getInstance().getOut() == null) {
            labelMessage.setText("Не удалось получить поток вывода.");
            labelMessage.setVisible(true);
            return;
        }

        // Логируем запрос
        System.out.println("Отправляем запрос: " + new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();

        // Получаем ответ от сервера
        if (ClientSocket.getInstance() != null && ClientSocket.getInstance().getInStream() != null) {
            String serverResponse = ClientSocket.getInstance().getInStream().readLine();
            if (serverResponse == null) {
                labelMessage.setText("Не получен ответ от сервера.");
                labelMessage.setVisible(true);
                return;
            }

            // Логируем ответ от сервера
            System.out.println("Ответ от сервера: " + serverResponse);

            // Преобразуем ответ в объект Response
            Response responseModel = new Gson().fromJson(serverResponse, Response.class);

            // Проверка на null
            if (responseModel != null && responseModel.getResponseData() != null) {
                // Десериализуем данные клиента из responseData
                Client loggedInClient = new Gson().fromJson(responseModel.getResponseData(), Client.class);
                System.out.println("Логин клиента: " + loggedInClient.getLogin());
                System.out.println("Пароль клиента: " + loggedInClient.getPassword());

                if (responseModel.getResponseStatus() == ResponseStatus.OK) {
                    // Успешный логин
                    ClientSocket.getInstance().setUser(loggedInClient); // Сохраняем клиента в синглтоне
                    labelMessage.setVisible(false);

                    // Открытие соответствующего окна в зависимости от роли
                    String userRole = responseModel.getResponseMessage();
                    if ("Добро пожаловать, администратор!".equalsIgnoreCase(userRole)) {
                        showAlert("Connect", "Welcome back, Admin!");
                        // Загружаем сцену администратора
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu_admin.fxml"));
                        Parent root = loader.load(); // Загружаем FXML
                        Admin controller = loader.getController();  // Получаем контроллер для администратора
                        controller.setClient(this.client);  // Передаем ссылку на объект клиента
                        controller.admin_get_login_and_password(login, password);
                        controller.update_from_authorization(login, password);
                        root.getStylesheets().add(getClass().getResource("/style/dashboardDesign.css").toExternalForm());
                        Scene newScene = new Scene(root);

                        Stage stage = (Stage) authorization_connect.getScene().getWindow();
                        stage.setScene(newScene);
                    }
                    else if ("Добро пожаловать, пользователь!".equalsIgnoreCase(userRole)) {
                        showAlert("Connect", "Welcome back, User!");

                        // Загружаем сцену пользователя
                        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/menu_user.fxml"));
                        Parent root1 = loader1.load();
                        User controller2 = loader1.getController();  // Получаем контроллер для пользователя
                        controller2.setClient(this.client);  // Передаем ссылку на объект клиента
                        controller2.admin_get_login_and_password2(login, password);
                        controller2.update_from_authorization2(login, password);
                        root1.getStylesheets().add(getClass().getResource("/style/dashboardDesign.css").toExternalForm());
                        Scene newScene = new Scene(root1);
                        Stage stage1 = (Stage) authorization_connect.getScene().getWindow();
                        stage1.setScene(newScene);
                    }
                } else {
                    labelMessage.setText("Неверный логин или пароль.");
                    labelMessage.setVisible(true);
                }
            } else {
                labelMessage.setText("Ошибка в ответе от сервера. Отсутствуют данные клиента.");
                labelMessage.setVisible(true);
            }
        } else {
            labelMessage.setText("Ошибка подключения к серверу.");
            labelMessage.setVisible(true);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Utility.ClientSocket;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Создание первого окна
        createWindow(primaryStage, "JBank Authorization");

        // Создание второго окна
       Stage secondStage = new Stage();
       createWindow(secondStage, "JBank Authorization");
    }

    private void createWindow(Stage stage, String title) throws Exception {
        ClientSocket.getInstance().getSocket();
        Parent root = FXMLLoader.load(getClass().getResource("/client.fxml"));
        stage.setTitle(title);
        stage.setScene(new Scene(root, 480, 640));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);  // Запускаем одно приложение с двумя окнами
    }
}

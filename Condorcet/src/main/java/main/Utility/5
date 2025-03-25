package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.chart.AreaChart;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.paint.Color;
import javafx.scene.image.WritableImage;  // Импортируем WritableImage

import javax.imageio.ImageIO;
import java.io.*;
import java.sql.*;
import javafx.stage.FileChooser;

public class Diagramme extends Application {

    @Override
    public void start(Stage stage) {
        initUI(stage);
    }

    private void initUI(Stage stage) {
        HBox root = new HBox();
        Scene scene = new Scene(root, 490, 350);

        // Ось X и ось Y для графика
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("ФИО");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Зарплата");

        // Создаем график AreaChart с типами данных String и Number
        AreaChart<String, Number> areaChart = new AreaChart<>(xAxis, yAxis);
        areaChart.setTitle("Зарплата сотрудников");

        // Создаем серию данных
        XYChart.Series<String, Number> data = new XYChart.Series<>();
        data.setName("Сотрудники");

        // Извлекаем данные из базы данных и добавляем в серию данных
        try {
            // Подключаемся к базе данных MS SQL Server с отключением SSL
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Data2;encrypt=false;trustServerCertificate=true";
            String username = "newlogin";
            String password = "1111";

            // Создание соединения с базой данных
            Connection connection = DriverManager.getConnection(url, username, password);

            // Выполнение SQL-запроса
            Statement statement = connection.createStatement();
            String query = "SELECT ФИО, Зарплата FROM UserID WHERE Зарплата IS NOT NULL";
            ResultSet resultSet = statement.executeQuery(query);

            // Добавляем данные в график
            while (resultSet.next()) {
                String fio = resultSet.getString("ФИО");
                double salary = resultSet.getDouble("Зарплата");
                data.getData().add(new XYChart.Data<>(fio, salary));
            }

            // Закрываем соединение с базой данных
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();  // Выводим ошибку, если она произошла
        }

        // Добавляем данные в график
        areaChart.getData().add(data);

        // Убираем легенду
        areaChart.setLegendVisible(false);

        // Добавляем график на сцену
        root.getChildren().add(areaChart);

        // Сохранение графика как изображения
        saveChartAsImage(areaChart, stage);

        // Сохранение данных в CSV
        saveDataToCSV(data, stage);

        // Загрузка данных из CSV
        XYChart.Series<String, Number> loadedData = loadDataFromCSV();
        areaChart.getData().clear();
        areaChart.getData().add(loadedData);

        // Настройка сцены и отображение
        stage.setTitle("График зарплат сотрудников");
        stage.setScene(scene);
        stage.show();
    }

    // Метод для сохранения графика как изображения
    private void saveChartAsImage(AreaChart<String, Number> areaChart, Stage stage) {
        // Захватить график в изображение
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);  // Устанавливаем прозрачный фон

        // Снимок графика
        WritableImage image = areaChart.snapshot(parameters, null);  // Теперь работает, так как импортирован WritableImage

        // Открываем диалог выбора файла для сохранения
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        fileChooser.setTitle("Сохранить график как изображение");
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                System.out.println("График сохранен как изображение в файл: " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Метод для сохранения данных в CSV
    private void saveDataToCSV(XYChart.Series<String, Number> data, Stage stage) {
        // Открываем диалог выбора файла для сохранения
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        fileChooser.setTitle("Сохранить данные в CSV");
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("ФИО,Зарплата\n");  // Заголовки столбцов
                for (XYChart.Data<String, Number> dataPoint : data.getData()) {
                    String fio = dataPoint.getXValue();
                    Number salary = dataPoint.getYValue();
                    writer.write(fio + "," + salary + "\n");
                }
                System.out.println("Данные сохранены в файл " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Метод для загрузки данных из CSV
    private XYChart.Series<String, Number> loadDataFromCSV() {
        XYChart.Series<String, Number> data = new XYChart.Series<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("chart_data.csv"))) {
            String line;
            reader.readLine(); // Пропускаем строку заголовков
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String fio = parts[0];
                double salary = Double.parseDouble(parts[1]);
                data.getData().add(new XYChart.Data<>(fio, salary));
            }
            System.out.println("Данные загружены из файла chart_data.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

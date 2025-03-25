package main.JDBC;

import java.sql.SQLException;

public class TestDatabase
{

    public static void main(String[] argv) throws SQLException {

        System.out.println("-------- MS SQL JDBC Connection Testing ------------");

        // Попытка подключения к базе данных
        JDBC.connect(); // Здесь подключаемся через метод из класса JDBC
        //JDBC.display_Admin_A();

        // Если подключение успешно, закрываем его
        JDBC.close();
    }
}

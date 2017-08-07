package com.realdolmen.jdbc;

import java.sql.*;

public class Exercise {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root",""))
        {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS book");
            statement.execute("CREATE TABLE IF NOT EXISTS book(id INTEGER NOT NULL, title VARCHAR(255), author VARCHAR(255), PRIMARY KEY(id))");
            statement.execute("INSERT INTO book VALUES(1, 'The Expanse', 'James SA Corey')");
            statement.execute("INSERT INTO book VALUES(2, 'Games of Thrones', 'GRR Martin')");
            ResultSet resultSet = statement.executeQuery("SELECT id,title, author FROM book");

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                System.out.printf("%d %s %s%n", id, title, author);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

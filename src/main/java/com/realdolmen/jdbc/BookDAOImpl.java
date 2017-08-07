package com.realdolmen.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO{

    @Override
    public void createBook(Book b) {
        try (Connection connection = ConnectionFactory.INSTANCE.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO book(id, title, author) VALUES(?,?,?)");
            preparedStatement.setInt(1,b.getId());
            preparedStatement.setString(2,b.getTitle());
            preparedStatement.setString(3,b.getAuthor());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> findBooksByTitle(String title) {
        try (Connection connection = ConnectionFactory.INSTANCE.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, title, author FROM book WHERE title LIKE ?");
            //like zorgt ervoor dat de titel niet exact moet zijn; voor of na of tussen
            //je vraagteken moet je vervangen door een setter;
            preparedStatement.setString(1,title+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(new Book(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("author")));
            }
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book findBook(Book id) {
        return null;

    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        try (Connection connection = ConnectionFactory.INSTANCE.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, title, author FROM book WHERE author LIKE ?");
            preparedStatement.setString(1,author+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(new Book(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author")));
            }
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

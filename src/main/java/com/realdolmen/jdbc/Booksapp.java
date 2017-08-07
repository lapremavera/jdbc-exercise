package com.realdolmen.jdbc;

import java.util.List;

public class Booksapp {
    public static void main(String[] args) {
        BookDAO dao = new BookDAOImpl();
        //dao.createBook(new Book(3,"Game of cards","Some One"));
        List<Book> Books = dao.findBooksByTitle("Game");
        Books.forEach(System.out::println);


        List<Book> Books1 = dao.findBooksByAuthor("Some");
        Books1.forEach(System.out::println);



    }
}

package com.realdolmen.jdbc;

import java.util.List;

public interface BookDAO {
    void createBook(Book b);
    List<Book> findBooksByTitle(String title);

    Book findBook (Book id);
    List<Book> findBooksByAuthor(String author);




}

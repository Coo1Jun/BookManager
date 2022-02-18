package com.book.service;

import com.book.entity.Book;
import com.book.entity.Borrow;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface BookService {
    List<Borrow> getBorrowList();

    void returnBookById(int id);

    List<Book> getActiveBookList();

    void addBorrow(int sid, int bid, Date time);

    Map<Book,Boolean> getBookList();

    void delBookById(int bid);

    void addBook(String title,String desc,double price);

    int getBookCount();

    int getBorrowCount();
}

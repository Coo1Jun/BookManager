package com.book.dao;

import com.book.entity.Book;
import com.book.entity.Borrow;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

public interface BookMapper {

    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "bid",property = "book_id"),
            @Result(column = "title",property = "book_name"),
            @Result(column = "time",property = "time"),
            @Result(column = "name",property = "student_name"),
            @Result(column = "sid",property = "student_id")
    })
    @Select("select * from borrow inner join student on borrow.sid = student.sid" +
            " inner join book on borrow.bid = book.bid")
    List<Borrow> getBorrowList();

    @Delete("delete from borrow where id = #{id}")
    void delBorrowById(int id);

    @Select("select * from book")
    List<Book> getBookList();

//    @Insert("insert into borrow values(null,#{sid},#{bid}, NOW())"),时间可以调用NOW()函数
    @Insert("insert into borrow values(null,#{sid},#{bid},#{time})")
    void addBorrow(@Param("sid") int sid, @Param("bid") int bid, @Param("time")Date time);

    @Delete("delete from book where bid = #{bid}")
    void deleteBook(int bid);

    @Insert("insert into book(title,`desc`,price) values(#{title},#{desc},#{price})")
    void addBook(@Param("title") String title,@Param("desc") String desc, @Param("price") double price);

    @Select("SELECT COUNT(*) FROM book")
    int getBookCount();

    @Select("SELECT COUNT(*) FROM borrow")
    int getBorrowCount();
}

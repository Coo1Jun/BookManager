package com.book.service.impl;

import com.book.dao.BookMapper;
import com.book.dao.UserMapper;
import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.entity.User;
import com.book.service.BookService;
import com.book.utils.MybatisUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;

import java.sql.Date;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {
    //单例模式
    private static BookServiceImpl service = null;
    private BookServiceImpl() {
    }
    public static BookServiceImpl getService(){
        if (service == null){
            service = new BookServiceImpl();
        }
        return service;
    }


    @Override
    public List<Borrow> getBorrowList() {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            return mapper.getBorrowList();
        }
    }

    @Override
    public void returnBookById(int id) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            mapper.delBorrowById(id);
        }
    }

    @Override
    public List<Book> getActiveBookList() {
        Set<Integer> set = new HashSet<>();
        this.getBorrowList().forEach(borrow -> set.add(borrow.getBook_id()));
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            return mapper.getBookList()
                    .stream()
                    .filter(book -> !set.contains(book.getBid()))//不在set里面的，即没有被借出去的书
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void addBorrow(int sid, int bid, Date time) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            mapper.addBorrow(sid, bid, time);
        }
    }

    @Override
    public Map<Book, Boolean> getBookList() {
        Set<Integer> set = new HashSet<>();
        this.getBorrowList().forEach(borrow -> set.add(borrow.getBook_id()));
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            Map<Book,Boolean> map = new LinkedHashMap<>();
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            mapper.getBookList().forEach(book -> map.put(book, set.contains(book.getBid())));
            return map;
        }
    }

    @Override
    public void delBookById(int bid) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            mapper.deleteBook(bid);
        }
    }

    @Override
    public void addBook(String title, String desc, double price) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            mapper.addBook(title, desc, price);
        }
    }

    @Override
    public int getBookCount() {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            return mapper.getBookCount();
        }
    }

    @Override
    public int getBorrowCount() {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            return mapper.getBorrowCount();
        }
    }
}


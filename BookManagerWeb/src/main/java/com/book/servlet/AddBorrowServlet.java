package com.book.servlet;

import com.book.dao.BookMapper;
import com.book.entity.Book;
import com.book.entity.Student;
import com.book.entity.User;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.service.impl.StudentServiceImpl;
import com.book.utils.MybatisUtil;
import com.book.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/add-borrow")
public class AddBorrowServlet extends HttpServlet {

    BookService bookService;
    StudentServiceImpl studentService;

    @Override
    public void init() throws ServletException {
        bookService = BookServiceImpl.getService();
        studentService = StudentServiceImpl.getService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("nickname", user.getNickname());
        context.setVariable("username", user.getUsername());

        List<Book> activeBookList = bookService.getActiveBookList();
        context.setVariable("activeBookList",activeBookList);

        List<Student> studentList = studentService.getStudentList();
        context.setVariable("studentList",studentList);

        ThymeleafUtil.doProcess("add-borrow.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bid = Integer.parseInt(req.getParameter("book_id"));
        int sid = Integer.parseInt(req.getParameter("student_id"));
        Date time = new Date(new java.util.Date().getTime());
        bookService.addBorrow(sid,bid,time);
//        resp.setContentType("text/html;UTF-8");
//        resp.getWriter().write("添加成功！");
//        resp.getWriter().write("<script language='javascript'>alert('删除成功')</script>");
        resp.sendRedirect("index");
    }
}

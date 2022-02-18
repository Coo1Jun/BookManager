package com.book.servlet;

import com.book.entity.User;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.service.impl.StudentServiceImpl;
import com.book.service.impl.UserServiceImpl;
import com.book.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    BookService bookService;
    StudentServiceImpl studentService;
    UserServiceImpl userService;
    @Override
    public void init() throws ServletException {
        bookService = BookServiceImpl.getService();
        studentService = StudentServiceImpl.getService();
        userService = UserServiceImpl.getService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("nickname",user.getNickname());
        context.setVariable("username",user.getUsername());
        context.setVariable("borrow_list",bookService.getBorrowList());

        context.setVariable("book_count",bookService.getBookCount());
        context.setVariable("student_count",studentService.getStudentCount());
        context.setVariable("borrow_count",bookService.getBorrowCount());
        ThymeleafUtil.doProcess("index.html", context, resp.getWriter());
    }
}

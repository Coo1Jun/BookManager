package com.book.servlet;

import com.book.entity.User;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.service.impl.StudentServiceImpl;
import com.book.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/students")
public class StudentsServlet extends HttpServlet {
    StudentServiceImpl studentService;

    @Override
    public void init() throws ServletException {
        studentService = StudentServiceImpl.getService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("nickname", user.getNickname());
        context.setVariable("username", user.getUsername());

        context.setVariable("students",studentService.getStudentList());
        ThymeleafUtil.doProcess("students.html", context, resp.getWriter());
    }
}

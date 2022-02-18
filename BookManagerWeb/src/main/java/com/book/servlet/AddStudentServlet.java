package com.book.servlet;

import com.book.entity.User;
import com.book.service.impl.StudentServiceImpl;
import com.book.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {
    StudentServiceImpl service;

    @Override
    public void init() throws ServletException {
        service = StudentServiceImpl.getService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("nickname", user.getNickname());
        context.setVariable("username", user.getUsername());
        ThymeleafUtil.doProcess("add-student.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        int grade = Integer.parseInt(req.getParameter("grade"));
        service.addStudent(name,sex,grade);
        resp.sendRedirect("students");
    }
}

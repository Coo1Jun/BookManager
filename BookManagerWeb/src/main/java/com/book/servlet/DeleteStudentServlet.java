package com.book.servlet;

import com.book.service.impl.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-student")
public class DeleteStudentServlet extends HttpServlet {
    StudentServiceImpl service;

    @Override
    public void init() throws ServletException {
        service = StudentServiceImpl.getService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sid = Integer.parseInt(req.getParameter("sid"));
        service.delStudentById(sid);
        resp.sendRedirect("students");
    }
}

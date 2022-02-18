package com.book.servlet;

import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-book")
public class DeleteBookServlet extends HttpServlet {
    BookService service;
    @Override
    public void init() throws ServletException {
        service = BookServiceImpl.getService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bid = req.getParameter("bid");
        service.delBookById(Integer.parseInt(bid));
//        resp.getWriter().write("<script language='javascript'>alert('删除成功')</script>");
        resp.sendRedirect("books");
    }
}

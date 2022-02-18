package com.book.servlet;

import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import com.book.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserService service;

    @Override
    public void init() throws ServletException {
        service = UserServiceImpl.getService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        //接收登录是否成功的标志
        if(req.getSession().getAttribute("login-failure") != null){
            context.setVariable("failure",true);
            req.getSession().removeAttribute("login-failure");//显示一次登录失败后就清空标志
        }
        if(req.getSession().getAttribute("user") != null && "on".equals(req.getSession().getAttribute("remember"))){
            resp.sendRedirect("index");
            return;
        }
        ThymeleafUtil.doProcess("login.html", context, resp.getWriter());
    }

    //登录用post请求
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember-me");
        if (service.auth(username, password, req.getSession(),remember)) {
//            resp.setContentType("text/html;UTF-8");
//            resp.getWriter().write("登录成功！");
            resp.sendRedirect("index");
        } else {
            //登录失败，随便丢一个不为空的实例
            req.getSession().setAttribute("login-failure", new Object());
            this.doGet(req, resp);
        }
    }
}

package com.book.service.impl;

import com.book.dao.UserMapper;
import com.book.entity.User;
import com.book.service.UserService;
import com.book.utils.MybatisUtil;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

public class UserServiceImpl implements UserService {
    //单例模式
    private static UserServiceImpl service = null;
    private UserServiceImpl(){

    }
    public static UserServiceImpl getService(){
        if (service == null){
            service = new UserServiceImpl();
        }
        return service;
    }
    @Override
    public boolean auth(String username, String password, HttpSession session, String remember) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUser(username, password);

            if (user == null) {
                return false;
            }
            //登录成功后
            session.setAttribute("user",user);
            session.setAttribute("remember",remember);
            return true;
        }
    }

    @Override
    public int getAdminCount() {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.getAdminCount();
        }
    }
}

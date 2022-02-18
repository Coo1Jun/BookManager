package com.book.service.impl;

import com.book.dao.StudentMapper;
import com.book.entity.Student;
import com.book.service.StudentService;
import com.book.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    //单例模式
    private static StudentServiceImpl service = null;
    private StudentServiceImpl(){

    }
    public static StudentServiceImpl getService(){
        if (service == null){
            service = new StudentServiceImpl();
        }
        return service;
    }
    @Override
    public List<Student> getStudentList() {
        try(SqlSession session = MybatisUtil.getSession()){
            StudentMapper mapper = session.getMapper(StudentMapper.class);
            return mapper.getStudentList();
        }
    }

    @Override
    public void delStudentById(int sid) {
        try(SqlSession session = MybatisUtil.getSession()){
            StudentMapper mapper = session.getMapper(StudentMapper.class);
            mapper.delStudentById(sid);
        }
    }

    @Override
    public void addStudent(String name, String sex, int grade) {
        try(SqlSession session = MybatisUtil.getSession()){
            StudentMapper mapper = session.getMapper(StudentMapper.class);
            mapper.addStudent(name, sex, grade);
        }
    }

    @Override
    public int getStudentCount() {
        try(SqlSession session = MybatisUtil.getSession()){
            StudentMapper mapper = session.getMapper(StudentMapper.class);
            return mapper.getStudentCount();
        }
    }
}

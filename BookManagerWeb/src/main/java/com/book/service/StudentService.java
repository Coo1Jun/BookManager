package com.book.service;

import com.book.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudentList();

    void delStudentById(int sid);

    void addStudent(String name,String sex,int grade);

    int getStudentCount();

}

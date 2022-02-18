package com.book.dao;

import com.book.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.List;

public interface StudentMapper {

    @Select("select * from student")
    List<Student> getStudentList();

    @Delete("delete from student where sid = #{sid}")
    void delStudentById(int sid);

    @Insert("insert into student(name,sex,grade) values(#{name},#{sex},#{grade})")
    void addStudent(@Param("name") String name, @Param("sex") String sex, @Param("grade") int grade);

    @Select("SELECT COUNT(*) FROM student")
    int getStudentCount();
}

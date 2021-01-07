package com.nhsoft.lemon.service;

import com.nhsoft.lemon.dto.PageDTO;
import com.nhsoft.lemon.dto.StudentDTO;
import com.nhsoft.lemon.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wanglei
 */
public interface StudentService{
    /**
     *  查询所有学生信息并且分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Student> listAllStudents(int pageNo,int pageSize);


}

package com.nhsoft.lemon.service;


import com.nhsoft.lemon.dto.StudentDTO;
import com.nhsoft.lemon.dto.TeacherDTO;
import com.nhsoft.lemon.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wanglei
 */
public interface TeacherService{
    /**
     *  查询所有教师信息并且分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<TeacherDTO> listAllTeacher(int pageNo, int pageSize);
}

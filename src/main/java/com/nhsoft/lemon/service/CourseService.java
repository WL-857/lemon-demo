package com.nhsoft.lemon.service;

import com.nhsoft.lemon.dto.CourseDTO;
import com.nhsoft.lemon.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanglei
 */
public interface CourseService {

    /**
     * 查询所有的课程并分页
     * @param pageNo
     * @param pageSize
     * @return
     */

    List<CourseDTO> listAllCourse(int pageNo, int pageSize);
}

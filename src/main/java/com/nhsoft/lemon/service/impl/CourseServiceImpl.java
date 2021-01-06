package com.nhsoft.lemon.service.impl;

import com.nhsoft.lemon.repository.CourseDao;
import com.nhsoft.lemon.dto.CourseDTO;
import com.nhsoft.lemon.mapstruct.CourseDTOConverter;
import com.nhsoft.lemon.model.Course;
import com.nhsoft.lemon.service.CourseService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.List;

/**
 * @author wanglei
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseDao courseDao;

    @Override
    public List<CourseDTO> listAllCourse(int pageNo, int pageSize) {
        if(pageNo<= 0){
            pageNo = 1;
        }
        if(pageSize <= 0){
            pageSize = 5;
        }
        PageRequest page = PageRequest.of(pageNo-1, pageSize);
        List<Course> courses = courseDao.listAllCourse(page);
        List<CourseDTO>  courseDTOS = CourseDTOConverter.INSTANCE.domain2dto(courses);
        return courseDTOS;
    }
}

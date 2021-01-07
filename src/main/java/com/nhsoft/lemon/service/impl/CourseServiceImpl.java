package com.nhsoft.lemon.service.impl;

import com.nhsoft.lemon.repository.CourseDao;
import com.nhsoft.lemon.dto.CourseDTO;

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
    public List<Course> listAllCourse(int pageNo, int pageSize) {
        if(pageNo<= 0){
            pageNo = 1;
        }
        if(pageSize <= 0){
            pageSize = 5;
        }
        PageRequest page = PageRequest.of(pageNo-1, pageSize);
        List<Course> courses = courseDao.listAllCourse(page);
        return courses;
    }

    @Override
    public Course readCourse(String no) {
        return courseDao.readCourse(no);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseDao.save(course);
    }

    @Override
    public List<Course> batchSaveCourse(List<Course> courses) {
        List<Course> list = courseDao.saveAll(courses);
        return list;
    }

    @Override
    public void deleteCourse(Long id) {
        courseDao.deleteById(id);
    }

    @Override
    public void batchDeleteCourse(List<Course> courses) {
        courseDao.deleteInBatch(courses);
    }
}

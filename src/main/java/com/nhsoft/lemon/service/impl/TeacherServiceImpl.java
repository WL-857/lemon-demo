package com.nhsoft.lemon.service.impl;

import com.nhsoft.lemon.repository.TeacherDao;
import com.nhsoft.lemon.dto.TeacherDTO;

import com.nhsoft.lemon.model.Teacher;
import com.nhsoft.lemon.service.TeacherService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wanglei
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherDao teacherDao;

    @Override
    public List<Teacher> listAllTeacher(int pageNo, int pageSize) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 5;
        }
        PageRequest page = PageRequest.of(pageNo-1, pageSize);
        List<Teacher> teachers = teacherDao.listAllTeacher(page);
        return teachers;
    }
}

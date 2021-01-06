package com.nhsoft.lemon.service.impl;

import com.nhsoft.lemon.repository.StudentDao;
import com.nhsoft.lemon.dto.StudentDTO;
import com.nhsoft.lemon.mapstruct.StudentDTOConverter;
import com.nhsoft.lemon.model.Student;
import com.nhsoft.lemon.service.StudentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wanglei
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentDao studentDao;

    @Override
    public List<StudentDTO> listAllStudents(int pageNo,int pageSize) {
        if(pageNo<= 0){
            pageNo = 1;
        }
        if(pageSize <= 0){
            pageSize = 5;
        }
        PageRequest page = PageRequest.of(pageNo-1, pageSize);
        List<Student> students = studentDao.listAllStudents(page);
        List<StudentDTO> studentDTOS = StudentDTOConverter.INSTANCE.domain2dto(students);
        return studentDTOS;
    }
}

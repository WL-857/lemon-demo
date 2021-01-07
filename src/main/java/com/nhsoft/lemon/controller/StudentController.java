package com.nhsoft.lemon.controller;

import com.nhsoft.lemon.dto.ScoreDTO;
import com.nhsoft.lemon.dto.StudentDTO;
import com.nhsoft.lemon.model.Student;
import com.nhsoft.lemon.model.extend.ScoreExtend;
import com.nhsoft.lemon.response.R;
import com.nhsoft.lemon.service.ScoreService;
import com.nhsoft.lemon.service.StudentService;
import com.nhsoft.lemon.utils.CopyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanglei
 */
@RestController
@Api("学生信息模块")
@Slf4j
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ScoreService scoreService;

    @ApiOperation("查询所有学生信息并分页")
    @GetMapping("/list/{pageNo}/{pageSize}")
    public R listAllStudents(@PathVariable(name = "pageNo",required = true) int pageNo,
                             @PathVariable(name = "pageSize",required = true) int pageSize) {
        log.info("listAllStudents开始执行，参数为：pageNo=" + pageNo + ",pageSize=" + pageSize);

        if (pageNo < 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 5;
        }
        List<Student> students = studentService.listAllStudents(pageNo,pageSize);
        if (CollectionUtils.isEmpty(students)) {
            return R.error("数据为空");
        }
        List<StudentDTO> studentDTOS = CopyUtil.toList(students, StudentDTO.class);
        log.info("方法执行结束，方法返回值为：" + studentDTOS);
        return R.ok().put("studentDTOS", studentDTOS);
    }

    @ApiOperation("根据学号查询学生所有的成绩")
    @GetMapping("/grade/{stuNo}/{year}")
    public R listStudentAllGrade(@PathVariable(value = "stuNo",required = true) String stuNo,
                                 @PathVariable(value = "year",required = true) String year){
        if (StringUtils.isEmpty(stuNo) || StringUtils.isEmpty(year)) {
            return R.error("请输入学号和年份");
        }
        List<ScoreExtend> scoreExtends = scoreService.listStudentAllGrade(stuNo, year);
        List<ScoreDTO> scoreDTOS = CopyUtil.toList(scoreExtends, ScoreDTO.class);

        return R.ok().put("scoreDTOS",scoreDTOS);
    }


}

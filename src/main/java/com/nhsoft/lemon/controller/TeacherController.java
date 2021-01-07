package com.nhsoft.lemon.controller;

import com.nhsoft.lemon.dto.AvgMaxMinDTO;
import com.nhsoft.lemon.dto.ScoreDTO;
import com.nhsoft.lemon.dto.StudentDTO;
import com.nhsoft.lemon.dto.TeacherDTO;
import com.nhsoft.lemon.model.Teacher;
import com.nhsoft.lemon.model.extend.ScoreExtend;
import com.nhsoft.lemon.model.extend.TeacherExtend;
import com.nhsoft.lemon.response.R;
import com.nhsoft.lemon.service.ScoreService;
import com.nhsoft.lemon.service.StudentService;
import com.nhsoft.lemon.service.TeacherService;
import com.nhsoft.lemon.utils.CopyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wanglei
 */
@RestController
@Api("教师信息信息模块")
@Slf4j
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ScoreService scoreService;

    @ApiOperation("查询所有教师信息并分页")
    @GetMapping("/list/{pageNo}/{pageSize}")
    public R listAllTeacher(@PathVariable(name = "pageNo",required = true) int pageNo,
                            @PathVariable(name = "pageSize",required = true) int pageSize) {
        log.info("listAllTeacher开始执行，参数为：pageNo=" + pageNo + ",pageSize=" + pageSize);

        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 5;
        }
        List<Teacher> teachers = teacherService.listAllTeacher(pageNo, pageSize);
        if (CollectionUtils.isEmpty(teachers)) {
            return R.error("数据为空");
        }
        List<TeacherDTO> teacherDTOS = CopyUtil.toList(teachers, TeacherDTO.class);
        return R.ok().put("teacherDTOS", teacherDTOS);
    }

    @ApiOperation("教师查询其所教授科目的成绩最大值，最小值，平均值")
    @GetMapping("/score/{teachNo}/{year}")
    public R readMaxAndMinAndAvgScore(@PathVariable(value = "teachNo",required = true) String teachNo,
                                      @PathVariable(value = "year",required = true) String year){
        log.info("readMaxAndMinAndAvgScore开始执行，参数为：teachNo=" + teachNo + ",year=" + year);
        List<TeacherExtend> scoreExtends = scoreService.readMaxAndMinAndAvgScoreByTeachNo(teachNo, year);
        if (CollectionUtils.isEmpty(scoreExtends)) {
            return R.error("数据为空");
        }
        List<AvgMaxMinDTO> avgMaxMinDTOS = CopyUtil.toList(scoreExtends, AvgMaxMinDTO.class);

        return R.ok().put("avgMaxMinDTOS",avgMaxMinDTOS);

    }

    @ApiOperation("查询学年所有学生每门学科的成绩最大值，最小值，平均值")
    @GetMapping("/score/{year}")
    public R listAllMaxMinAvgScore(@PathVariable(value = "year",required = true) String year){
        log.info("listAllMaxMinAvgScore开始执行，参数为：year=" + year);
        List<TeacherExtend> teacherExtends = scoreService.listAllMaxMinAvgScore(year);
        if (CollectionUtils.isEmpty(teacherExtends)) {
            return R.error("数据为空");
        }

        List<AvgMaxMinDTO> avgMaxMinDTOS = CopyUtil.toList(teacherExtends, AvgMaxMinDTO.class);
        return R.ok().put("avgMaxMinDTOS",avgMaxMinDTOS);
    }
}

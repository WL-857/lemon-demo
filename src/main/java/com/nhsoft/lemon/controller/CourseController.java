package com.nhsoft.lemon.controller;

import com.nhsoft.lemon.dto.CourseDTO;
import com.nhsoft.lemon.dto.StudentDTO;
import com.nhsoft.lemon.model.Course;
import com.nhsoft.lemon.response.R;
import com.nhsoft.lemon.service.CourseService;
import com.nhsoft.lemon.service.StudentService;
import com.nhsoft.lemon.utils.CopyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wanglei
 */
@RestController
@Api("课程信息模块")
@Slf4j
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation("查询所有课程信息并分页")
    @GetMapping("/list/{pageNo}/{pageSize}")
    public R listAllCourse(@PathVariable(name = "pageNo",required = true) int pageNo,
                             @PathVariable(name = "pageSize",required = true) int pageSize) {
        log.info("listAllCourse方法开始执行,参数为：pageNo=" + pageNo + ",pageSize=" + pageSize);

        if (pageNo < 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 5;
        }
        List<Course> courses = courseService.listAllCourse(pageNo,pageSize);
        if (CollectionUtils.isEmpty(courses)) {
            return R.error("数据为空");
        }
        List<CourseDTO> courseDTOS = CopyUtil.toList(courses, CourseDTO.class);
        log.info("方法执行结束，方法返回值为：" + courseDTOS);
        return R.ok().put("courseDTOS", courseDTOS);
    }

    @ApiOperation("根据课程编号查询课程信息")
    @GetMapping("/{courseNo}")
    public R readCourse(@PathVariable(name = "courseNo",required = true) String courseNo) {
        log.info("readCourse方法开始执行,参数为：courseNo=" + courseNo);
        if(StringUtils.isEmpty(courseNo)){
            return R.error("参数为空");
        }
        Course course = courseService.readCourse(courseNo);
            if (course == null) {
            return R.error("数据为空");
        }
        CourseDTO courseDTO = CopyUtil.to(course, CourseDTO.class);
        log.info("方法执行结束，方法返回值为：" + courseDTO);
        return R.ok().put("courseDTOS", courseDTO);
    }

    @ApiOperation("添加课程")
    @PostMapping("/save")
    public R saveCourse(@RequestBody CourseDTO courseDTO){
        if(ObjectUtils.isEmpty(courseDTO)){
            return R.error("参数为空，请输入参数");
        }
        Course course = CopyUtil.to(courseDTO, Course.class);

        Course saveCourse = courseService.saveCourse(course);
        if(saveCourse == null){
            return R.error("添加失败");
        }
        return R.ok("添加成功");
    }

    @ApiOperation("添加课程")
    @PostMapping(value = "/batchSave")
    public R batchSaveCourse(@RequestBody List<CourseDTO> courseDTOs){
        if(CollectionUtils.isEmpty(courseDTOs)){
            return R.error("参数为空，请输入参数");
        }
        List<Course> courses = CopyUtil.toList(courseDTOs, Course.class);

        List<Course> saveCourse = courseService.batchSaveCourse(courses);
        if(CollectionUtils.isEmpty(saveCourse)){
            return R.error("添加失败");
        }
        return R.ok("添加成功");
    }

    @ApiOperation("删除课程")
    @DeleteMapping(value = "/delete/{id}")
    public R deleteCourse(@PathVariable(value = "id",required = true) Long id){

        if(ObjectUtils.isEmpty(id)){
            return R.error("参数为空，请输入参数");
        }

        courseService.deleteCourse(id);

        return R.ok("删除成功");
    }

    @ApiOperation("批量删除课程")
    @PostMapping(value = "/delete")
    public R deleteCourse(List<Course> ids){

        if(ObjectUtils.isEmpty(ids)){
            return R.error("参数为空，请输入参数");
        }

        courseService.batchDeleteCourse(ids);

        return R.ok("删除成功");
    }
}

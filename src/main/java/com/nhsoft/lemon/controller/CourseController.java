package com.nhsoft.lemon.controller;

import com.nhsoft.lemon.dto.CourseDTO;
import com.nhsoft.lemon.dto.StudentDTO;
import com.nhsoft.lemon.response.R;
import com.nhsoft.lemon.service.CourseService;
import com.nhsoft.lemon.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/list")
    public R listAllCourse(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
                             @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        log.info("listAllCourse，参数为：pageNo=" + pageNo + ",pageSize=" + pageSize);

        if (pageNo < 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 5;
        }
        List<CourseDTO> courseDTOS = courseService.listAllCourse(pageNo,pageSize);
        if (CollectionUtils.isEmpty(courseDTOS)) {
            return R.error("数据为空");
        }
        log.info("方法执行结束，方法返回值为：" + courseDTOS);
        return R.ok().put("courseDTOS", courseDTOS);
    }
}

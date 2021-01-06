package com.nhsoft.lemon.controller;

import com.nhsoft.lemon.dto.StudentDTO;
import com.nhsoft.lemon.dto.TeacherDTO;
import com.nhsoft.lemon.response.R;
import com.nhsoft.lemon.service.StudentService;
import com.nhsoft.lemon.service.TeacherService;
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
@Api("教师信息信息模块")
@Slf4j
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("查询所有教师信息并分页")
    @GetMapping("/list")
    public R listAllTeacher(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
                            @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        log.info("listAllTeacher开始执行，参数为：pageNo=" + pageNo + ",pageSize=" + pageSize);

        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 5;
        }
        List<TeacherDTO> teacherDTOS = teacherService.listAllTeacher(pageNo, pageSize);
        if (CollectionUtils.isEmpty(teacherDTOS)) {
            return R.error("数据为空");
        }
        return R.ok().put("teacherDTOS", teacherDTOS);
    }
}

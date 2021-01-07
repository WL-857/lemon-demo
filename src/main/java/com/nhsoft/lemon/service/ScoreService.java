package com.nhsoft.lemon.service;


import com.nhsoft.lemon.dto.ScoreDTO;
import com.nhsoft.lemon.model.Score;
import com.nhsoft.lemon.model.extend.ScoreExtend;
import com.nhsoft.lemon.model.extend.TeacherExtend;

import java.util.List;

/**
 * @author wanglei
 */
public interface ScoreService {
    /**
     * 根据学号获取学生所有的课程成绩
     * @param stuNo
     * @param time
     * @return
     */
    List<ScoreExtend> listStudentAllGrade(String stuNo, String time);

    /**
     * 教师根据其编号和学年查询其所教授科目的成绩最大值，最小值，平均值
     * @param teachNo
     * @param time
     * @return
     */
    List<TeacherExtend>  readMaxAndMinAndAvgScoreByTeachNo(String teachNo, String time);

    /**
     * 查询学年所有学生每门学科的成绩最大值，最小值，平均值
     * @param year
     * @return
     */
    List<TeacherExtend> listAllMaxMinAvgScore(String year);
}

package com.nhsoft.lemon.service;


import com.nhsoft.lemon.dto.ScoreDTO;
import com.nhsoft.lemon.model.Score;

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
    List<ScoreDTO> listStudentAllGrade(String stuNo, String time);
}

package com.nhsoft.lemon.repository;

import com.nhsoft.lemon.dto.ScoreDTO;
import com.nhsoft.lemon.model.Score;
import com.nhsoft.lemon.model.extend.ScoreExtend;
import com.nhsoft.lemon.model.extend.TeacherExtend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wanglei
 */
@Repository
public interface ScoreDao extends JpaRepository<Score,Long> {

    /**
     * 根据学号获取其所有的成绩
     * @param stuNo
     * @param year
     * @return
     */
    @Query(value = "select new com.nhsoft.lemon.model.extend.ScoreExtend(s.grade,c.couName,s.time,stu.stuName) from Score s " +
            "left join Course c  on c.couId = s.cId " +
            "left join Student stu on stu.stuId = s.sId " +
            "where stu.stuNo = ?1 and s.time = ?2 order by s.time")
    List<ScoreExtend> listStudentAllGrade(String stuNo, String year);

    /**
     * 根据教师编号查询其所教授课程的最大成绩、最小成绩、和平均成绩
     * @param teachId
     * @param year
     * @return
     */
    @Query(value = "select new com.nhsoft.lemon.model.extend.TeacherExtend(c.couName,max(s.grade),min(s.grade),avg(s.grade)) from Score s " +
            "left join Course c on c.couId = s.cId " +
            "left join TeacherCourseMapping tcm on tcm.cId = c.couId " +
            "where tcm.tId = ?1 and s.time= ?2 group by c.couId")
    List<TeacherExtend> listMaxMinAvgScore(Long teachId, String year);

    /**
     * 查询学年所有学生每门学科的成绩最大值，最小值，平均值
     * @param year
     * @return
     */
    @Query(value = "select new com.nhsoft.lemon.model.extend.TeacherExtend(c.couName,max(s.grade),min(s.grade),s.time,avg(s.grade)) from Score s " +
            "left join Course c on c.couId = s.cId " +
            "left join Student s2  on s2.stuId  = s.sId " +
            "where s.time = ?1 group by c.couId")
    List<TeacherExtend> listAllMaxMinAvgScore(String year);
}
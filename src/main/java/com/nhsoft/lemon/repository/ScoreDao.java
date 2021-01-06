package com.nhsoft.lemon.repository;

import com.nhsoft.lemon.dto.ScoreDTO;
import com.nhsoft.lemon.model.Score;
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
    @Query(value = "select new com.nhsoft.lemon.dto.ScoreDTO(s.grade,c.couName,s.time,stu.stuName) from Score s " +
            "left join Course c  on c.couId = s.cId " +
            "left join Student stu on stu.stuId = s.sId " +
            "where stu.stuNo = ?1 and s.time = ?2 order by s.time")
    List<ScoreDTO> listStudentAllGrade(String stuNo, String year);
}
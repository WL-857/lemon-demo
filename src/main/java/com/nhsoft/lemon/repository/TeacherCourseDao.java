package com.nhsoft.lemon.repository;

import com.nhsoft.lemon.model.TeacherCourse;
import com.nhsoft.lemon.model.extend.TeacherExtend;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

/**
 * @author wanglei
 */
public interface TeacherCourseDao extends JpaRepository<TeacherCourse,Long> {

    /**
     * 查询所有老师所教授的课程并分页
     * @param pageable
     * @return
     */
    @Query(value = "select new com.nhsoft.lemon.model.extend.TeacherExtend(t.teachName,c.couName) from Teacher t " +
            "left join TeacherCourse  tc on tc.teachId = t.teachId " +
            "left join Course  c on c.couId = tc.couId")
    List<TeacherExtend> listTeacherCourse(Pageable pageable);

}

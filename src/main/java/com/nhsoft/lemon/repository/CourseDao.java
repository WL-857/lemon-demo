package com.nhsoft.lemon.repository;

import com.nhsoft.lemon.model.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author wanglei
 */
public interface CourseDao extends JpaRepository<Course,Long> {


    /**
     * 查询所有学生信息冰粉也
     * @param pageable
     * @return
     */
    @Query(value = "select * from course",nativeQuery = true)
    List<Course> listAllCourse(Pageable pageable);

    /**
     * 根据课程编号查询课程信息
     * @param id
     * @return
     */
    @Query(value = "select new com.nhsoft.lemon.model.Course(c.couName,c.couNo) from Course c " +
            "where c.couId = ?1")
    Course readCourse(Long id);



}

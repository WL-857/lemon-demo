package com.nhsoft.lemon.repository;

import com.nhsoft.lemon.model.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
}

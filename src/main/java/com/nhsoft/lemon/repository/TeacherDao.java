package com.nhsoft.lemon.repository;

import com.nhsoft.lemon.model.Teacher;
import com.nhsoft.lemon.model.extend.ScoreExtend;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author wanglei
 */
public interface TeacherDao extends JpaRepository<Teacher,Long> {
    /**
     * 查询所有教师信息并且分页
     * @param pageable
     * @return
     */
    @Query(value = "select * from teacher",nativeQuery = true)
    List<Teacher> listAllTeacher(Pageable pageable);


}

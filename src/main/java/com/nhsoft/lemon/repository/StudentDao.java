package com.nhsoft.lemon.repository;

import com.nhsoft.lemon.model.Student;
import lombok.Lombok;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author wanglei
 */
public interface StudentDao extends JpaRepository<Student, Long> {

    /**
     * 查询所有学生信息冰粉也
     * @param pageable
     * @return
     */
    @Query(value = "select * from student",nativeQuery = true)
    List<Student> listAllStudents(Pageable pageable);
}

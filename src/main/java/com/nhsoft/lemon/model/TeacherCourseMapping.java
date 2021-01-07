package com.nhsoft.lemon.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author wanglei
 */
@Data
@Entity
@Table(name = "teacher_course_mapping")
public class TeacherCourseMapping implements Serializable {

    @Id
    private Long tId;
    @Id
    private Long cId;
}

package com.nhsoft.lemon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 学生实体类
 * @author wanglei
 */
@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

    private static final long serialVersionUID = -8077158237459001744L;
    /**
     * 学生id,也是主键，自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long stuId;

    /**
     * 学生姓名
     */

    private String stuName;

    /**
     * 学生性别
     */

    private Integer stuSex;

    /**
     * 学生学号
     */

    private String stuNo;

    /**
     * 学生手机号
     */

    private String stuPhone;

//    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
//    private Set<Course> courses = new HashSet<>();
//
//    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
//    private Set<Teacher> teachers = new HashSet<>();
}

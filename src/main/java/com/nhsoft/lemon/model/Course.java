package com.nhsoft.lemon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 课程实体类
 * @author wanglei
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course implements Serializable {

    private static final long serialVersionUID = 7305069259917484908L;
    /**
     * 课程id,也是主键，自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long couId;

    /**
     * 课程名称
     */

    private String couName;

    /**
     * 课程编号
     */

    private String couNo;

    public Course(String couName, String couNo) {
        this.couName = couName;
        this.couNo = couNo;
    }
}

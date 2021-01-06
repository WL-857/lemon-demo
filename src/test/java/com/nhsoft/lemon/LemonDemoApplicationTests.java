package com.nhsoft.lemon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhsoft.lemon.dto.ScoreDTO;
import com.nhsoft.lemon.model.Score;
import com.nhsoft.lemon.model.Student;
import com.nhsoft.lemon.repository.ScoreDao;
import com.nhsoft.lemon.repository.StudentDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@SpringBootTest
class LemonDemoApplicationTests {

    @PersistenceContext
    public EntityManager entityManager;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ScoreDao scoreDao;
    @Test
    void contextLoads() throws JsonProcessingException {
        Student student = new Student(1L, "张三", 1, "2016211689", "18326528028");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(student);
        System.out.println(json);
    }
    @Test
    void test2() throws JsonProcessingException {
//        List<Student> students = studentDao.listAllStudents();
//        System.out.println(students);
    }@Test
    void test3() throws JsonProcessingException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        criteriaQuery.from(Student.class);
        TypedQuery<Student> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(0);
        typedQuery.setMaxResults(5);
        List<Student> resultList = typedQuery.getResultList();
        System.out.println(resultList);
    }@Test
    void test4()  {
        List<ScoreDTO> scores = scoreDao.listStudentAllGrade("2020211001","2020");
        System.out.println(scores);
    }
}

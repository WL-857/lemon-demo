package com.nhsoft.lemon.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhsoft.lemon.config.RedisKey;
import com.nhsoft.lemon.dto.ScoreDTO;
import com.nhsoft.lemon.model.Course;
import com.nhsoft.lemon.model.Score;
import com.nhsoft.lemon.model.Student;
import com.nhsoft.lemon.model.extend.ScoreExtend;
import com.nhsoft.lemon.model.extend.TeacherExtend;
import com.nhsoft.lemon.repository.ScoreDao;
import com.nhsoft.lemon.service.ScoreService;
import com.nhsoft.lemon.utils.CopyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanglei
 */
@Service
@Transactional
public class ScoreServiceImpl implements ScoreService {
    @Resource
    private ScoreDao scoreDao;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public List<ScoreExtend> listAllScore(int pageNo, int pageSize) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 5;
        }
        PageRequest page = PageRequest.of(pageNo - 1, pageSize);
        List<ScoreExtend> scoreExtends = scoreDao.listAllScore(page);
        return scoreExtends;
    }

    @Override
    public List<ScoreExtend> listStudentAllGrade(String stuNo, String time) {
        if (StringUtils.isEmpty(stuNo) || StringUtils.isEmpty(time)) {
            return new ArrayList<ScoreExtend>();
        }
        List<ScoreExtend> scoreExtends = scoreDao.listStudentAllGrade(stuNo, time);
        if(CollectionUtils.isEmpty(scoreExtends)){
            return new ArrayList<ScoreExtend>();
        }
        return scoreExtends;
    }

    @Override
    public List<TeacherExtend> listMaxMinAvgScore(Long teachId, String year) {

            if (ObjectUtils.isEmpty(teachId) || StringUtils.isEmpty(year)) {
                return new ArrayList<TeacherExtend>();
            }
            List<TeacherExtend> scoreExtends = scoreDao.listMaxMinAvgScore(teachId, year);
            if(CollectionUtils.isEmpty(scoreExtends)){
                return new ArrayList<TeacherExtend>();
            }
            return scoreExtends;

    }


    @Override
    public List<TeacherExtend> listAllMaxMinAvgScore(String year) {
        if (StringUtils.isEmpty(year)) {
            return new ArrayList<TeacherExtend>();
        }
        List<TeacherExtend> teacherExtends = scoreDao.listAllMaxMinAvgScore(year);
        if(CollectionUtils.isEmpty(teacherExtends)){
            return new ArrayList<TeacherExtend>();
        }
        return teacherExtends;
    }

    @Override
    public Score saveScore(Score score) {
        Score save = scoreDao.save(score);
        if (save == null) {
            return save;
        }
        Long scoId = save.getScoId();
        String scoreKey = RedisKey. SCORE_KEY+ scoId;
        Object scoreValue = redisTemplate.opsForValue().get(scoreKey);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String scoreJson = objectMapper.writeValueAsString(score);
            if (scoreValue == null) {
                redisTemplate.opsForValue().set(scoreKey, scoreJson);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return save;
    }

    @Override
    public List<Score> batchSaveScore(List<Score> scores) {
        List<Score> list = scoreDao.saveAll(scores);
        list.forEach(course -> {
            String scoreKey = RedisKey.SCORE_KEY + course.getCouId();
            Object value = redisTemplate.opsForValue().get(scoreKey);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String scoreJson = objectMapper.writeValueAsString(course);
                if (value == null) {
                    redisTemplate.opsForValue().set(scoreKey, scoreJson);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        return list;
    }

    @Override
    public void deleteScore(Long id) {
        String scoreKey = RedisKey.SCORE_KEY + id;
        Object value = redisTemplate.opsForValue().get(scoreKey);
        if (value != null) {
            redisTemplate.delete(scoreKey);
        }
        if (scoreDao.getOne(id) != null) {

            scoreDao.deleteById(id);
        }
    }

    @Override
    public void batchDeleteScore(List<Long> ids) {
        ids.forEach(id -> {
            String scoreKey = RedisKey.SCORE_KEY + id;
            Object value = redisTemplate.opsForValue().get(scoreKey);
            if (value != null) {
                redisTemplate.delete(scoreKey);
            }
            if (scoreDao.getOne(id) != null) {
                scoreDao.deleteById(id);
            }
        });
    }

    @Override
    public ScoreExtend readScore(Long id) {
        return scoreDao.readScore(id);
    }
}

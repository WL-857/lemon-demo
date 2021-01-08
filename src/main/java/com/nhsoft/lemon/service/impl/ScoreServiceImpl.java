package com.nhsoft.lemon.service.impl;

import com.nhsoft.lemon.dto.ScoreDTO;
import com.nhsoft.lemon.model.Score;
import com.nhsoft.lemon.model.extend.ScoreExtend;
import com.nhsoft.lemon.model.extend.TeacherExtend;
import com.nhsoft.lemon.repository.ScoreDao;
import com.nhsoft.lemon.service.ScoreService;
import com.nhsoft.lemon.utils.CopyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanglei
 */
@Service
public class ScoreServiceImpl implements ScoreService {
    @Resource
    private ScoreDao scoreDao;

    @Resource
    private RedisTemplate redisTemplate;

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
}

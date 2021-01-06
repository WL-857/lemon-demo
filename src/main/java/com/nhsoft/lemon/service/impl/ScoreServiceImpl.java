package com.nhsoft.lemon.service.impl;

import com.nhsoft.lemon.dto.ScoreDTO;
import com.nhsoft.lemon.model.Score;
import com.nhsoft.lemon.repository.ScoreDao;
import com.nhsoft.lemon.service.ScoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    @Override
    public List<ScoreDTO> listStudentAllGrade(String stuNo, String time) {
        if (StringUtils.isEmpty(stuNo) || StringUtils.isEmpty(time)) {
            return new ArrayList<ScoreDTO>();
        }
        List<ScoreDTO> scoreDTOS = scoreDao.listStudentAllGrade(stuNo, time);
        if(CollectionUtils.isEmpty(scoreDTOS)){
            return new ArrayList<ScoreDTO>();
        }
        return scoreDTOS;
    }
}

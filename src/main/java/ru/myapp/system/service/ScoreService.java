package ru.myapp.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myapp.system.dao.ScoreDao;
import ru.myapp.system.dto.ScoreDto;
import ru.myapp.system.model.Score;
import ru.myapp.system.utils.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreService {

    @Autowired
    ScoreDao scoreDao;

    public List<ScoreDto> getAllScore(){
        List<Score> scores = scoreDao.getAllScore();
        return scores.stream().map(Mapper::map).collect(Collectors.toList());
    }

    public void updateScore(Score score){
        scoreDao.updateScore(score.getId());
    }
}

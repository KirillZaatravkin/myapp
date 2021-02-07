package ru.myapp.system.utils;

import ru.myapp.system.dto.QuestionDto;
import ru.myapp.system.dto.ScoreDto;
import ru.myapp.system.model.Question;
import ru.myapp.system.model.Score;

public final class Mapper {

    private Mapper() {
    }

    public static QuestionDto map(Question question) {
        QuestionDto result = new QuestionDto();
        result.setDescription(question.getDescription());
        result.setFile(question.getFile());
        result.setFilebaty(question.getFilebaty());
        result.setFlag(question.isFlag());
        result.setId(question.getId());
        result.setOtvet(question.getOtvet());
        result.setTitle(question.getTitle());
        return result;
    }

    public static ScoreDto map (Score score){
        ScoreDto result = new ScoreDto();
        result.setId(score.getId());
        result.setScore(score.getScore());
        result.setTitle(score.getTitle());
        return result;
    }
}

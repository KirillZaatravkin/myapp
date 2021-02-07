package ru.myapp.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myapp.system.dao.QuestionDao;
import ru.myapp.system.dto.QuestionDto;
import ru.myapp.system.model.Question;
import ru.myapp.system.utils.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;

    public List<QuestionDto>getAllQuestion() {
        List<Question> listQuestion = questionDao.getAllQuestion();
        return listQuestion.stream().map(Mapper::map).collect(Collectors.toList());
    }

    public void addQuestion(Question question) {
        questionDao.addQuestion(question);
    }

    public QuestionDto getQuestion(int id) {
        Question question = questionDao.getQuestion(id);
        return Mapper.map(question);
    }

    public QuestionDto getQuestionWithImage(int id) throws SQLException {
        Question question = questionDao.getQuestionWithImage(id);
        return Mapper.map(question);
    }

    public void closeQuestion(Question question) {
      questionDao.closeQuestion(question.getId());
    }
}

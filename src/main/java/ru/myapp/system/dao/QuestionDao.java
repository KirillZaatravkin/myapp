package ru.myapp.system.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import ru.myapp.system.model.Question;
import ru.myapp.system.utils.HibernateSession;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Repository
public class QuestionDao {

    public List<Question> getAllQuestion() {
        String hql = "FROM Question order by id";
        List<Question> result = (List<Question>) HibernateSession.getSessionFactory().openSession().createQuery(hql).list();
        return result;
    }

    public void addQuestion(Question Question) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Question.setFlag(false);
        session.save(Question);
        session.close();
    }

    public Question getQuestion(int id) {
        Session session = HibernateSession.getSessionFactory().openSession();
        return session.get(Question.class, id);
    }

    public Question getQuestionWithImage(int id) throws SQLException {
        Session session = HibernateSession.getSessionFactory().openSession();
        Question result = session.get(Question.class, id);
        if (result != null) {
            if (result.getFilebaty() != null) {
                result.setFileBlob(new SerialBlob(result.getFilebaty()));
            }
        }
        return session.get(Question.class, id);
    }

    public void closeQuestion(int id) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Question temp = getQuestion(id);

        if (temp == null)
            return;//TODO нужен обработчик

        temp.setFlag(true);
        session.saveOrUpdate(temp);
        session.close();
    }

    public void addFile(int id , MultipartFile file) throws IOException {
        Session session = HibernateSession.getSessionFactory().openSession();
        Question temp = getQuestion(id);

        if (temp == null)
            return; //TODO нужен обработчик

        temp.setFile(file.getOriginalFilename());
        temp.setFilebaty(file.getBytes());
        session.saveOrUpdate(temp);
        session.close();
    }
}

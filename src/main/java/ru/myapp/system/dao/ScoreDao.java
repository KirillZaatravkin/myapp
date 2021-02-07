package ru.myapp.system.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.myapp.system.model.Score;
import ru.myapp.system.utils.HibernateSession;

import java.util.List;

@Repository
public class ScoreDao {

    public List<Score> getAllScore(){
        String hql = "FROM Score order by id";
        List<Score> result = (List<Score>)  HibernateSession.getSessionFactory().openSession().createQuery(hql).list();
        return  result;
    }

    public Score getScore(int id){
        Session session = HibernateSession.getSessionFactory().openSession();
        return session.get(Score.class,id);
    }

    public void updateScore(int id){
        Score scoreTemp = getScore(id);
        if (scoreTemp == null)
            return; //TODO нужен обработчик

        scoreTemp.setScore(id);
        Session session = HibernateSession.getSessionFactory().openSession();
        session.update(scoreTemp);
        session.close();
    }
}

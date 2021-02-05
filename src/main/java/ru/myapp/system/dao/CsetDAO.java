package ru.myapp.system.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.myapp.system.model.Cset;
import ru.myapp.system.utils.HibernateSession;

import java.util.List;

@Repository
public class CsetDAO {

    public List<Cset> getAllCset(){
        String hql = "FROM Cset order by id";
        List<Cset> result = (List<Cset>)  HibernateSession.getSessionFactory().openSession().createQuery(hql).list();
        return  result;
    }

    public Cset getCset(int id){
        Session session = HibernateSession.getSessionFactory().openSession();
        return session.get(Cset.class,id);
    }

    public void updateCset(Cset cset){

        Cset csetTemp = getCset(cset.getId());
        int csetInt = cset.getCset();
        csetTemp.setCset(csetInt);

        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(csetTemp);
        tx1.commit();
        session.close();
    }
}

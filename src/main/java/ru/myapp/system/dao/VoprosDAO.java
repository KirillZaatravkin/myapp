package ru.myapp.system.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import ru.myapp.system.model.Vopros;
import ru.myapp.system.utils.HibernateSession;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VoprosDAO {

    public List<Vopros> getAllVopros() {
        String hql = "FROM Vopros order by id";
        List<Vopros> result = (List<Vopros>) HibernateSession.getSessionFactory().openSession().createQuery(hql).list();
        return result;
    }

    public void addVopros(Vopros Vopros) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Vopros.setFlag(false);

        session.save(Vopros);
        tx1.commit();
        session.close();
    }

    public Vopros getVopros(int id) {
        Session session = HibernateSession.getSessionFactory().openSession();
        return session.get(Vopros.class, id);
    }

    public Vopros getProductWithImage(int id) throws SQLException {
        Session session = HibernateSession.getSessionFactory().openSession();
        Vopros result = session.get(Vopros.class, id);
        if (result != null) {
            if (result.getFilebaty() != null) {
                result.setFileBlob(new SerialBlob(result.getFilebaty()));
            }
        }
        return session.get(Vopros.class, id);
    }

    public void closeVopros(Vopros vopros) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Vopros temp = getVopros(vopros.getId());

        if (temp == null)
            return;//TODO нужен обработчик

        Transaction tx1 = session.beginTransaction();
        temp.setFlag(true);
        session.saveOrUpdate(temp);
        tx1.commit();
        session.close();
    }

    public void addFile(Vopros vopros, MultipartFile file) throws IOException {
        Session session = HibernateSession.getSessionFactory().openSession();
        Vopros temp = getVopros(vopros.getId());
        if (temp == null)
            return; //TODO нужен обработчик
        Transaction tx1 = session.beginTransaction();
        temp.setFile(file.getOriginalFilename());
        temp.setFilebaty(file.getBytes());
        session.saveOrUpdate(temp);
        tx1.commit();
        session.close();
    }
}

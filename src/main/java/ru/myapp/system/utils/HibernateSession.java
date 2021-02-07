package ru.myapp.system.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.myapp.system.model.Score;
import ru.myapp.system.model.Question;

public class HibernateSession {
    private static SessionFactory sessionFactory;
    private HibernateSession() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Question.class);
                configuration.addAnnotatedClass(Score.class);

                sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Error!" + e);
            }
        }
        return sessionFactory;
    }
}

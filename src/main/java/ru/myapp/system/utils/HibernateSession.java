package ru.myapp.system.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.myapp.system.model.Cset;
import ru.myapp.system.model.Vopros;

public class HibernateSession {
    private static SessionFactory sessionFactory;
    private HibernateSession() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Vopros.class);
                configuration.addAnnotatedClass(Cset.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Error!" + e);
            }
        }
        return sessionFactory;
    }
}

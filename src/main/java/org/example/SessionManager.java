package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class SessionManager {
    private static final Configuration CONFIGURATION = new Configuration().configure();
    private static final SessionFactory SESSION_FACTORY = CONFIGURATION.buildSessionFactory();

    public static void saveAll(Object ...objects) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        for (int i = 0; i < objects.length; i++) {
            session.save(objects[i]);
        }

        session.getTransaction().commit();
        session.close();
    }

    public static List<Object> loadAll(Class clazz) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        CriteriaQuery<Object> criteriaQuery = session.getCriteriaBuilder().createQuery(clazz);
        criteriaQuery.select(criteriaQuery.from(clazz));
        List<Object> results = session.createQuery(criteriaQuery).getResultList();

        session.getTransaction().commit();
        session.close();

        return results;
    }

    public static void updateAll(Object ...objects) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        for (int i = 0; i < objects.length; i++) {
            session.update(objects[i]);
        }

        session.getTransaction().commit();
        session.close();
    }
}

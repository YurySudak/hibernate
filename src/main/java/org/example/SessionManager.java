package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class SessionManager {
    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    private static Session openSessionAndBeginTransaction() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        return session;
    }

    private static void commitTransactionAndCloseSession(Session session) {
        session.getTransaction().commit();
        session.close();
    }

    public static void saveAll(Object ...objects) {
        Session session = openSessionAndBeginTransaction();
        for (int i = 0; i < objects.length; i++) {
            session.save(objects[i]);
        }
        commitTransactionAndCloseSession(session);
    }

    public static List<Object> loadAll(Class clazz) {
        Session session = openSessionAndBeginTransaction();
        CriteriaQuery<Object> criteriaQuery = session.getCriteriaBuilder().createQuery(clazz);
        criteriaQuery.select(criteriaQuery.from(clazz));
        List<Object> results = session.createQuery(criteriaQuery).getResultList();
        commitTransactionAndCloseSession(session);
        return results;
    }

    public static void updateAll(Object ...objects) {
        Session session = openSessionAndBeginTransaction();
        for (int i = 0; i < objects.length; i++) {
            session.update(objects[i]);
        }
        commitTransactionAndCloseSession(session);
    }
}

package org.example.jpql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class JpqlManager {
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
        List results = session.createQuery("from " + clazz.getSimpleName()).list();
        commitTransactionAndCloseSession(session);
        return results;
    }

    public static List<String> loadNames(Class clazz) {
        Session session = openSessionAndBeginTransaction();
        List results = session.createQuery("select p.name from " + clazz.getSimpleName() + " p").list();
        commitTransactionAndCloseSession(session);
        return results;
    }

    public static List<Object> loadCostLessThen(Class clazz, double cost) {
        Session session = openSessionAndBeginTransaction();
        List results = session
                .createQuery("select p from " + clazz.getSimpleName() + " p where p.cost < " + cost)
                .list();
        commitTransactionAndCloseSession(session);
        return results;
    }

    public static List<String> loadLike(Class clazz, String string) {
        Session session = openSessionAndBeginTransaction();
        List results = session
                .createQuery("select p.name from " + clazz.getSimpleName() + " p where p.name like '" + string + "'")
                .list();
        commitTransactionAndCloseSession(session);
        return results;
    }

    public static void updateCost(Class clazz, double cost, String name) {
        Session session = openSessionAndBeginTransaction();
        session.createQuery("update " + clazz.getSimpleName() + " p set p.cost = :cost where p.name = :name")
                .setParameter("cost", cost)
                .setParameter("name", name)
                .executeUpdate();
        commitTransactionAndCloseSession(session);
    }

    public static Object load(Class clazz, String name) {
        Session session = openSessionAndBeginTransaction();
        Object result = session
                .createQuery("select p from " + clazz.getSimpleName() + " p where p.name = '" + name + "'")
                .getSingleResult();
        commitTransactionAndCloseSession(session);
        return result;
    }
}

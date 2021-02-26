package org.example.jpqlcriteria;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

public class CriteriaManager {

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

    public static List<Product> loadNames(Class clazz) {
        Session session = openSessionAndBeginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root root = criteriaQuery.from(clazz);
        CompoundSelection name = criteriaBuilder.construct(clazz, root.get("name"));
        criteriaQuery.select(name);
        List results = session.createQuery(criteriaQuery).list();
        commitTransactionAndCloseSession(session);
        return results;
    }

    public static List<Object> loadCostLessThen(Class clazz, double cost) {
        Session session = openSessionAndBeginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root root = criteriaQuery.from(clazz);
        criteriaQuery.select(root)
                .where(criteriaBuilder.lt(root.get("cost"), cost));
        List results = session.createQuery(criteriaQuery).list();
        commitTransactionAndCloseSession(session);
        return results;
    }

    public static List<Product> loadLike(Class clazz, String string) {
        Session session = openSessionAndBeginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root root = criteriaQuery.from(clazz);
        criteriaQuery.select(root)
                .where(criteriaBuilder.like(root.get("name"), string));
        List results = session.createQuery(criteriaQuery).list();
        commitTransactionAndCloseSession(session);
        return results;
    }

    public static void updateCost(Class clazz, double cost, String name) {
        Session session = openSessionAndBeginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaUpdate criteriaUpdate = criteriaBuilder.createCriteriaUpdate(clazz);
        Root root = criteriaUpdate.from(clazz);
        criteriaUpdate.set("cost", cost);
        criteriaUpdate.where(criteriaBuilder.equal(root.get("name"), name));
        session.createQuery(criteriaUpdate).executeUpdate();
        commitTransactionAndCloseSession(session);
    }

    public static Object load(Class clazz, String name) {
        Session session = openSessionAndBeginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root root = criteriaQuery.from(clazz);
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("name"), name));
        Object result = session.createQuery(criteriaQuery).getSingleResult();
        commitTransactionAndCloseSession(session);
        return result;
    }
}

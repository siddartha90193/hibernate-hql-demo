package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        // Load Hibernate Configuration
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Update Department entity using HQL with positional parameters
            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE id = ?3";
            int updatedEntities = session.createQuery(hql)
                                         .setParameter(1, "Updated Department Name")
                                         .setParameter(2, "Updated Location")
                                         .setParameter(3, 1) // Assuming Department ID = 1
                                         .executeUpdate();

            System.out.println("Number of records updated: " + updatedEntities);

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}

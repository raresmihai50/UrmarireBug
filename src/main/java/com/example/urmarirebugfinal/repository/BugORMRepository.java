package com.example.urmarirebugfinal.repository;

import com.example.urmarirebugfinal.domain.Bug;
import com.example.urmarirebugfinal.domain.StareBug;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BugORMRepository {
    private SessionFactory sessionFactory;

    public BugORMRepository(SessionFactory sessionFactory){this.sessionFactory = sessionFactory;}

    public List<Bug> getAll(){
        try(var session = sessionFactory.openSession()){
            return session.createQuery("FROM Bug", Bug.class).list();
        }
        catch (Exception e){
            return List.of();
        }
    }

    public Bug getOneById(int id){
        try(var session = sessionFactory.openSession()){
            Query<Bug> query = session.createQuery("FROM Bug WHERE id=:id");
            query.setParameter("id", id);
            return query.uniqueResult();
        }
        catch (Exception e){
            return null;
        }
    }

    public Bug save(String denumire, String descriere, StareBug stare){
        Transaction transaction = null;
        Bug bug = new Bug(denumire, descriere ,stare);
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(bug);
            transaction.commit();
            return bug;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public Bug update(Bug bug){
        Transaction transact = null;
        try (Session session = sessionFactory.openSession()) {
            try {
                transact = session.beginTransaction();
                String sql = "UPDATE Bug SET denumire=:denumire, descriere=:descriere WHERE id=:id";
                Query query = session.createQuery(sql);
                query.setParameter("denumire", bug.getDenumire());
                query.setParameter("descriere", bug.getDescriere());
                query.setParameter("id", bug.getId());
                query.executeUpdate();
                transact.commit();
            } catch (Exception e) {
                System.err.println("Eroare la update bug !!! " + e);
                if (transact != null) {
                    transact.rollback();
                }
            }
        }
        return bug;
    }

    public void rezolva (int id){
        Transaction transact = null;
        try (Session session = sessionFactory.openSession()) {
            try {
                transact = session.beginTransaction();
                String sql = "UPDATE Bug SET stare=:stare_noua WHERE id=:id";
                Query query = session.createQuery(sql);
                query.setParameter("stare_noua", StareBug.REZOLVAT);
                query.setParameter("id", id);
                query.executeUpdate();
                transact.commit();
            } catch (Exception e) {
                System.err.println("Eroare la update bug !!! " + e);
                if (transact != null) {
                    transact.rollback();
                }
            }
        }
    }
}

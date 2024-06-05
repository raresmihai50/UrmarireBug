package com.example.urmarirebugfinal.repository;

import com.example.urmarirebugfinal.domain.Programator;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProgramatorORMRepository {
    private SessionFactory sessionFactory;
    public ProgramatorORMRepository(SessionFactory sessionFactory){this.sessionFactory = sessionFactory;}
    public List<Programator> getAll(){
        try(var session = sessionFactory.openSession()){
            return session.createQuery("SELECT P FROM Programator P", Programator.class).list();
        }
        catch (Exception e){
            return List.of();
        }
    }
    public Programator getOneByUsername(String username){
        try(var session = sessionFactory.openSession()){
            var query = session.createQuery("SELECT P FROM Programator P " +
                    "WHERE P.username like :username", Programator.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        }
        catch (Exception e){
            return null;
        }
    }

    public void eliminaBug(){

    }
}

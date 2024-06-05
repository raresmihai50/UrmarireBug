package com.example.urmarirebugfinal.repository;

import com.example.urmarirebugfinal.domain.Verificator;
import org.hibernate.SessionFactory;

import java.util.List;

public class VerificatorORMRepository {
    private SessionFactory sessionFactory;
    public VerificatorORMRepository(SessionFactory sessionFactory){this.sessionFactory = sessionFactory;}
    public List<Verificator> getAll(){
        try(var session = sessionFactory.openSession()){
            return session.createQuery("SELECT V FROM Verificator V", Verificator.class).list();
        }
        catch (Exception e){
            return List.of();
        }
    }
    public Verificator getOneByUsername(String username){
        try(var session = sessionFactory.openSession()){
            var query = session.createQuery("SELECT V FROM Verificator V " +
                    "WHERE V.username like :username", Verificator.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        }
        catch (Exception e){
            return null;
        }
    }
}

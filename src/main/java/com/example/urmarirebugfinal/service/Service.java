package com.example.urmarirebugfinal.service;

import com.example.urmarirebugfinal.domain.Bug;
import com.example.urmarirebugfinal.domain.Programator;
import com.example.urmarirebugfinal.domain.StareBug;
import com.example.urmarirebugfinal.domain.Verificator;
import com.example.urmarirebugfinal.observer.IObserver;
import com.example.urmarirebugfinal.observer.Observable;
import com.example.urmarirebugfinal.repository.BugORMRepository;
import com.example.urmarirebugfinal.repository.ProgramatorORMRepository;
import com.example.urmarirebugfinal.repository.VerificatorORMRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Service implements Observable {
    VerificatorORMRepository verifRepo;
    ProgramatorORMRepository progRepo;
    BugORMRepository bugRepo;
    private List<IObserver<Bug>> observers = new ArrayList<>();

    public Service(ProgramatorORMRepository progRepo, VerificatorORMRepository verifRepo, BugORMRepository bugRepo) {
        this.progRepo = progRepo;
        this.verifRepo = verifRepo;
        this.bugRepo = bugRepo;
    }

    public List<Verificator> getAllVerif() {
        return verifRepo.getAll();
    }

    public Verificator getOneVerif(String username) {
        return verifRepo.getOneByUsername(username);
    }

    public List<Programator> getAllProg() {
        return progRepo.getAll();
    }

    public Programator getOneProg(String username) {
        return progRepo.getOneByUsername(username);
    }

    public Bug getOneBugById(int id) {
        return bugRepo.getOneById(id);
    }

    public Bug saveBug(String denumire, String descriere) {
        Bug bug = bugRepo.save(denumire, descriere, StareBug.NEREZOLVAT);
        notifyObservers(bug);
        return bug;
    }

    public List<Bug> getAllBugs() {
        return bugRepo.getAll();
    }

    public Bug updateBug(Bug bug){
        Bug bug_updatat = bugRepo.update(bug);
        notifyObservers(bug_updatat);
        return bug_updatat;
    }
    public void rezolvaBug(int id){
        bugRepo.rezolva(id);
        Bug bug = getOneBugById(id);
        notifyObservers(bug);
    }


    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Object t) {
        observers.forEach(x -> x.updateBug((Bug) t));
    }
}

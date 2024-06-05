package com.example.urmarirebugfinal.observer;

public interface Observable<E> {
    void addObserver(IObserver<E> observer);

    void removeObserver(IObserver<E> observer);

    void notifyObservers(E t);
}

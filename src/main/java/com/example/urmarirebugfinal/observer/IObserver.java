package com.example.urmarirebugfinal.observer;

import com.example.urmarirebugfinal.domain.Bug;

public interface IObserver<E> {
    void updateBug(Bug bug);
}
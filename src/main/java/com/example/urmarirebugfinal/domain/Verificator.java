package com.example.urmarirebugfinal.domain;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "Verificator" )
public class Verificator implements EntityInterface<Integer> {
    @Column(name="username")
    String username;
    @Column(name="id")
    int id;
    public Verificator(String username){
        this.username = username;
    }
    public Verificator(){}

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String toString() {
        return "Id=" + id + ", " + username;
    }

    @Override
    @Id
    @GeneratedValue(generator="increment")
    public Integer getId() {
        return null;
    }

    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Verificator verif = (Verificator) o;
        return id == verif.id && Objects.equals(username, verif.username);
    }
}

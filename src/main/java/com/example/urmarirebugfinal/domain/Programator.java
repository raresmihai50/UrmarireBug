package com.example.urmarirebugfinal.domain;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Objects;
import java.util.Properties;

@Entity
@Table( name = "Programator" )
public class Programator implements EntityInterface<Integer> {
    @Column(name="username")
    String username;
    @Column(name="id")
    int id;
    public Programator(String username){
        this.username = username;
    }
    public Programator(){}

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
        return id;
    }

    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programator prog = (Programator) o;
        return id == prog.id && Objects.equals(username, prog.username);
    }
}


package com.example.urmarirebugfinal.domain;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table( name = "Bug" )
public class Bug implements EntityInterface<Integer>{
    @Column(name="denumire")
    String denumire;
    @Column(name="descriere")
    String descriere;
    @Column(name="stare")
    StareBug stare;
    @Column(name="id")
    int id;
    public Bug(int id, String denumire, String descriere, StareBug stare){
        this.id = id;
        this.descriere = descriere;
        this.denumire = denumire;
        this.stare = stare;
    }
    public Bug(String denumire, String descriere, StareBug stare){
        this.descriere = descriere;
        this.denumire = denumire;
        this.stare = stare;
    }
    public Bug(){}

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

    @Column(name = "descriere")
    public String getDescriere(){
        return descriere;
    }

    public void setDescriere(String descriere){
        this.descriere = descriere;
    }

    @Column(name = "denumire")
    public String getDenumire(){
        return denumire;
    }

    public void setDenumire(String denumire){
        this.denumire = denumire;
    }

    @Enumerated(EnumType.STRING)
    @Column(name= "stare")
    public StareBug getStare(){
        return stare;
    }

    public void setStare(StareBug stare){
        this.stare = stare;
    }

    @Override
    public String toString(){return "Id= " + id+ ", " + denumire + ", " + descriere+ ", " + stare.toString();}
}

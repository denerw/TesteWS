package com.dener.testews.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "factories")
public class Factory {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "country_code")
    private long country_code;

//    @JsonIgnore
//    @OneToMany(mappedBy = "factory")
//    private Set<Car> cars = new HashSet<>();



    public Factory() {
        this.id = id;
        this.name = name;
        this.country_code = country_code;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getCountry_code() {
        return country_code;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry_code(long country_code) {
        this.country_code = country_code;
    }

//    public Set<Car> getCars() {
//        return cars;
//    }
//
//    public void setCars(Set<Car> cars) {
//        this.cars = cars;
//    }
}

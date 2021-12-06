package com.dener.testews.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "factory_id")
    private long factory_id;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private long year;

    @Column(name = "fuel")
    private String fuel;

    @Column(name = "doors")
    private long doors;

    @Column(name = "cost")
    private long cost;

    @Column(name = "color")
    private String color;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "factory_id", referencedColumnName = "id")
//    private Factory factory;

    public Car() {
        this.id = id;
        this.factory_id = factory_id;
        this.model = model;
        this.year = year;
        this.fuel = fuel;
        this.doors = doors;
        this.cost = cost;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFactory_id() {
        return factory_id;
    }

    public void setFactory_id(long factory_id) {
        this.factory_id = factory_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public long getDoors() {
        return doors;
    }

    public void setDoors(long doors) {
        this.doors = doors;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

//    public Factory getFactory() {
//        return factory;
//    }

//    public void setFactory(Factory factory) {
//        this.factory = factory;
//    }
}

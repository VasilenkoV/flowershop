package com.accenture.flowershop.be.entity.flower;

import java.math.BigDecimal;

public class Flower {
    private int id;
    private String name;
    private BigDecimal price;
    private int number;

    public Flower(){}

    public Flower(int id, String name, BigDecimal price, int number){
        this.id = id;
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() { return price;
    }

    public int getNumber() {
        return number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPrice(BigDecimal price) { this.price = price;
    }
}

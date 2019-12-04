package com.unasat.shop.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Produkt {

    @Id
    @GeneratedValue()
    private Long id;

    private String name;
    private double price;

    @ManyToMany(mappedBy = "produktList")
//@OneToMany (mappedBy="produkt")
@Column
    private List<Bestelling> bestellingList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Produkt(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

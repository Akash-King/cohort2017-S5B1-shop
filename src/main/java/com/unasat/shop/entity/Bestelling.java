package com.unasat.shop.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Bestelling {

    @Id
    @GeneratedValue()
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;
//    @OneToMany (mappedBy="bestelling")
    @ManyToMany(cascade = CascadeType.ALL)
    @Column
    @JoinTable(
            name = "bestelling_produkt",
            joinColumns = { @JoinColumn(name = "bestelling_id") },
            inverseJoinColumns = { @JoinColumn(name = "produkt_id") }
    )
    private List<Produkt> produktList;

    public Bestelling(Customer customer, List<Produkt> produktList) {
        this.customer = customer;
        this.produktList = produktList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Produkt> getProduktList() {
        return produktList;
    }

    public void setProduktList(List<Produkt> produktList) {
        this.produktList = produktList;
    }
}

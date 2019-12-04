package com.unasat.shop.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Customer {

    @Id
    @GeneratedValue()
    private Long id;

    @Column(name="first_name")
    private String firstName;
    private String lastName;
    private String creditcardNo;
    private Date birthDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="adres_id", nullable=false)
    private Adres adres;
    private String validateMe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCreditcardNo() {
        return creditcardNo;
    }

    public void setCreditcardNo(String creditcardNo) {
        this.creditcardNo = creditcardNo;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }
}


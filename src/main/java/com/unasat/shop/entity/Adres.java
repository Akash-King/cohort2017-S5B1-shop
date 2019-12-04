package com.unasat.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Adres {

    @Id
    @GeneratedValue()
    private Long id;

    private String distrikt;
    private String straat;
    private int huisnummer;

    public Adres(String distrikt, String straat, int huisnummer) {
        this.distrikt = distrikt;
        this.straat = straat;
        this.huisnummer = huisnummer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrikt() {
        return distrikt;
    }

    public void setDistrikt(String distrikt) {
        this.distrikt = distrikt;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }
}

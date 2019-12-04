package com.unasat.shop;

import com.unasat.shop.entity.Bestelling;
import com.unasat.shop.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class BestellingDaoJpa {

    private EntityManager entityManager;

    public BestellingDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Bestelling> findAll() {
        List<Bestelling> result = new ArrayList<>();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            result = entityManager.createQuery("SELECT b FROM Bestelling b").getResultList();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return result;
    }

    public void addBestelling(Bestelling bestelling) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(bestelling);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
}

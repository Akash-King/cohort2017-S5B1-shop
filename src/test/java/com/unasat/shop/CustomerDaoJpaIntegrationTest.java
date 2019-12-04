package com.unasat.shop;

import com.unasat.shop.entity.Adres;
import com.unasat.shop.entity.Bestelling;
import com.unasat.shop.entity.Customer;
import com.unasat.shop.entity.Produkt;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;

public class CustomerDaoJpaIntegrationTest {

    private CustomerDaoJpa customerDaoJpa;
    private BestellingDaoJpa bestellingDaoJpa;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Before
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("shop");
        entityManager = entityManagerFactory.createEntityManager();
        customerDaoJpa = new CustomerDaoJpa(entityManager);
        bestellingDaoJpa = new BestellingDaoJpa(entityManager);
    }

    @After
    public void tearDown() {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void shouldFindPreviouslySavedCustomer() {
        // given

        // Customer data
        Integer age = 22;
        String firstName = "John";
        String lastName = "Connor";
        Calendar calendar = Calendar.getInstance();
        calendar.set(1988, Calendar.FEBRUARY, 5);
        Date birthDate = calendar.getTime();

        // Adres data
        String distrikt = "Paramaribo";
        String straat = "Ringweg";
        int huisnummer = 3;

        // bestelling data
        List<Produkt> produktList = new ArrayList<>();
        produktList.add(new Produkt("Appel", 5.00));
        produktList.add(new Produkt("Peer", 7.50));

        // Create adres object
        Adres adres = new Adres(distrikt,straat,huisnummer);

        // Create customer object
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setBirthDate(birthDate);
        customer.setAdres(adres);

        // Create bestelling object with products (many to many)
        Bestelling bestelling = new Bestelling(customer,produktList);

        // Add customer data in db (adres data will be automatically inserted by reference)
        customerDaoJpa.addPerson(customer);

        // Add bestelling data in db (product data from list will be automatically addedand tussen tabel will be autmatically filled with foreign keys)
        bestellingDaoJpa.addBestelling(bestelling);

        // when
        List<Customer> result = customerDaoJpa.findAll(); // get customers from db
        List<Bestelling> bestellingResult = bestellingDaoJpa.findAll(); // get bestelling from db

        // then
        assertThat(result, hasSize(1));
        Customer foundCustomer = result.get(0);

        assertThat(foundCustomer.getBirthDate(), is(birthDate));
        assertThat(foundCustomer.getFirstName(), is(firstName));
        assertThat(foundCustomer.getLastName(), is(lastName));

        assertThat(bestellingResult, hasSize(1));
        Bestelling foundBestelling = bestellingResult.get(0);

//        assertThat(foundBestelling.getCustomer(), is(customer));
        assertThat(foundBestelling.getProduktList().size(), is(2));
    }
}

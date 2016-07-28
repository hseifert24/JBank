package com.jbank;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by localadmin on 7/28/16.
 */
public class BankTest {

    Bank b;
    @Before
    public void setUp() throws Exception {
        b = new Bank("US Bank");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testConstructorwithName() throws Exception {


        assertNotNull(b.getId());
        assertEquals(b.getName(), "US Bank");
        assertEquals(0, b.getClients().size());
    }

    @Test
    public void testAddClient() throws Exception {
        b.addClient("Bob");
        assertEquals(1, b.getClients().size());
        assertEquals(1, b.getClients().get(0).getName());
    }

}
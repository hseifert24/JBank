package com.jbank;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

/**
 * Created by localadmin on 7/28/16.
 */
public class AccountTest {

    Account acct;
    @Before
    public void setUp() throws Exception {
        acct = new Account(AccountType.CHECKING);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testConstructorwithType() throws Exception {

        Account a = new Account(AccountType.CHECKING);
        assertNotNull(a.getId());
        assertEquals(0f, a.getBalance(), 0);
        assertEquals(AccountType.CHECKING, a.getType());
        assertEquals(false, a.isClosed());
    }

    @Test
    public void testtoString() throws Exception {
        Account a = new Account(AccountType.CHECKING);
        assertThat(a.toString(), containsString("CHECKING 0.0"));

    }

    @Test
    public void testDeposit() throws Exception {
        acct.Deposit(100f);
        assertEquals(100f, acct.getBalance(), 0);
    }

    @Test
    public void testWithDraw() throws Exception {
        acct.Deposit(100f);
        acct.WithDraw(50f);
        assertEquals(50f, acct.getBalance(), 0);
    }

    @Test
    public void testWithDrawMoreThanBalance() throws Exception {
        acct.Deposit(100f);
        acct.WithDraw(200f);
        assertEquals(50f, acct.getBalance(), 0);
    }

    @Test
    public void testAccountNotClosedForTwoOverdraftTransactions() throws Exception {
        acct.Deposit(100f);
        acct.WithDraw(200f);
        acct.WithDraw(200f);
        assertEquals(false, acct.isClosed());
    }

    @Test
    public void testAccountClosedWhenThreeOverdraftTransactions() throws Exception {
        acct.Deposit(100f);
        acct.WithDraw(200f);
        acct.WithDraw(200f);
        acct.WithDraw(200f);
        assertEquals(true, acct.isClosed());
    }


    @Test(expected = AccountException.class)
    public void testDepostForClosedAccount() throws Exception {
        acct.Deposit(100f);
        acct.WithDraw(200f);
        acct.WithDraw(200f);
        acct.WithDraw(200f);
        acct.Deposit(100f);
    }

    @Test(expected = AccountException.class)
    public void testWithdrawForClosedAccount() throws Exception {
        acct.Deposit(100f);
        acct.WithDraw(200f);
        acct.WithDraw(200f);
        acct.WithDraw(200f);
        acct.WithDraw(100f);
    }
}
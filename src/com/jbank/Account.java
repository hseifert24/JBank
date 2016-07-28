package com.jbank;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by localadmin on 7/28/16.
 */
public class Account {
    private AccountType type;
    private float balance;
    private String id;
    private boolean isClosed;
    private ArrayList<Transaction> transactions;
    private int overDraftCount;


    public Account(AccountType type) {
        Random rand = new Random();
        this.type = type;
        this.id = String.valueOf(Math.abs(rand.nextLong()));
        this.balance = 0f;
        this.isClosed = false;
        this.transactions = new ArrayList<>();
        this.overDraftCount = 0;
    }

    public AccountType getType() {
        return type;
    }

    public float getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }

    public boolean isClosed() {
        return isClosed;
    }

    @Override
    public String toString() {
        return this.type.toString() + " " + this.balance + "  " + this.isClosed+ "  " + this.id + " " + this.overDraftCount;
    }

    public void Deposit(float amount) throws Exception {
        if (this.isClosed) {
            throw new AccountException("Your account is closed.");
        }
        Transaction transaction = new Transaction( amount, TransactionType.DEPOSIT);
        this.balance += amount;
        this.transactions.add(transaction);
    }

    public void WithDraw(float amount) throws Exception
    {
        if (this.isClosed) {
            throw new AccountException("Your account is closed.");
        }

        if (this.balance <= amount){
            Transaction transaction = new Transaction(50f, TransactionType.FEE);
            this.balance -= transaction.getAmount();
            this.transactions.add(transaction);
            this.overDraftCount++;

            if(this.overDraftCount == 3) {
                this.isClosed = true;
            }
        }else {
            Transaction transaction = new Transaction(amount, TransactionType.WITHDRAWAL);
            this.balance -= amount;
            this.transactions.add(transaction);
        }
    }


}

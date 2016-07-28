package com.jbank;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by localadmin on 7/28/16.
 */
public class Bank {
    private String id;
    private String name;
    private ArrayList<Client> clients;

    public Bank(String name) {
        this.name = name;
        this.id = String.valueOf(UUID.randomUUID());
        this.clients = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void addClient(String clientName)
    {
        this.clients.add(new Client(clientName));
    }
}

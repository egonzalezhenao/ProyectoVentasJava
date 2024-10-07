package com.proyecto.ventas;

public class Salesman {
    private long id;
    private String name;
    private String surname;
    private int totalSales;

    public Salesman(long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.totalSales = 0;
    }

    // M�todos Getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getTotalSales() {
        return totalSales;
    }

    // M�todo para agregar ventas
    public void addSales(int sales) {
        this.totalSales += sales;
    }
}

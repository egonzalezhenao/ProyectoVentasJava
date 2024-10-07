package com.proyecto.ventas;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantitySold;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantitySold = 0;
    }

    // M�todos Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    // M�todo para incrementar la cantidad vendida
    public void incrementQuantitySold(int quantity) {
        this.quantitySold += quantity;
    }
}

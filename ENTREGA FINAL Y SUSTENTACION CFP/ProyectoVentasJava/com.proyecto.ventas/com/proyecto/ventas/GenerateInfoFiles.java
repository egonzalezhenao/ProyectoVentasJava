package com.proyecto.ventas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GenerateInfoFiles {

    // Método para generar el archivo de ventas de un vendedor
    public void createSalesMenFile(int salesCount, String name, long id) {
        try (FileWriter fileWriter = new FileWriter("Sales_" + id + ".txt")) {
            Random random = new Random();
            for (int i = 0; i < salesCount; i++) {
                int productId = random.nextInt(10) + 1; // ID de producto entre 1 y 10
                int quantitySold = random.nextInt(100) + 1; // Cantidad vendida entre 1 y 100
                fileWriter.write("ProductoID: " + productId + ", CantidadVendida: " + quantitySold + "\n");
            }
            System.out.println("Archivo de ventas generado: Sales_" + id + ".txt");
        } catch (IOException e) {
            System.out.println("Error al generar archivo de ventas.");
            e.printStackTrace();
        }
    }

    // Método para generar el archivo de productos
    public void createProductsFile(int productsCount) {
        try (FileWriter fileWriter = new FileWriter("Products.txt")) {
            Random random = new Random();
            for (int i = 1; i <= productsCount; i++) {
                String productName = "Producto_" + i;
                double price = random.nextDouble() * 100; // Precio entre 0 y 100
                fileWriter.write("ID: " + i + ", Nombre: " + productName + ", Precio: " + price + "\n");
            }
            System.out.println("Archivo de productos generado: Products.txt");
        } catch (IOException e) {
            System.out.println("Error al generar archivo de productos.");
            e.printStackTrace();
        }
    }

    // Método para generar el archivo de vendedores
    public void createSalesManInfoFile(int salesmanCount) {
        try (FileWriter fileWriter = new FileWriter("SalesMenInfo.txt")) {
            for (int i = 1; i <= salesmanCount; i++) {
                String name = "Vendedor_" + i;
                String surname = "Apellido_" + i;
                long id = 123456789 + i;
                fileWriter.write("ID: " + id + ", Nombre: " + name + ", Apellido: " + surname + "\n");
            }
            System.out.println("Archivo de vendedores generado: SalesMenInfo.txt");
        } catch (IOException e) {
            System.out.println("Error al generar archivo de vendedores.");
            e.printStackTrace();
        }
    }

    // Método para leer el archivo de vendedores
    public Map<Long, Salesman> readSalesmen() {
        Map<Long, Salesman> salesmen = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("SalesMenInfo.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                long id = Long.parseLong(parts[0].split(": ")[1]);
                String name = parts[1].split(": ")[1];
                String surname = parts[2].split(": ")[1];
                Salesman salesman = new Salesman(id, name, surname);
                salesmen.put(id, salesman);
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo de vendedores.");
            e.printStackTrace();
        }
        return salesmen;
    }

    // Método para leer el archivo de productos
    public Map<Integer, Product> readProducts() {
        Map<Integer, Product> products = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Products.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                int id = Integer.parseInt(parts[0].split(": ")[1]);
                String name = parts[1].split(": ")[1];
                double price = Double.parseDouble(parts[2].split(": ")[1]);
                Product product = new Product(id, name, price);
                products.put(id, product);
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo de productos.");
            e.printStackTrace();
        }
        return products;
    }

    // Método para procesar ventas
    public void processSales(Map<Long, Salesman> salesmen, Map<Integer, Product> products, long id) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Sales_" + id + ".txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                int productId = Integer.parseInt(parts[0].split(": ")[1]);
                int quantitySold = Integer.parseInt(parts[1].split(": ")[1]);

                // Actualizar ventas en productos
                Product product = products.get(productId);
                if (product != null) {
                    product.incrementQuantitySold(quantitySold);
                }

                // Actualizar ventas en vendedores
                Salesman salesman = salesmen.get(id);
                if (salesman != null) {
                    salesman.addSales(quantitySold);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al procesar archivo de ventas: Sales_" + id + ".txt");
            e.printStackTrace();
        }
    }

    // Método para generar el reporte de vendedores
    public void generateSalesmenReport(Map<Long, Salesman> salesmen) {
        try (FileWriter fileWriter = new FileWriter("SalesmenReport.csv")) {
            fileWriter.write("ID, Nombre, Apellido, TotalVentas\n");
            for (Salesman salesman : salesmen.values()) {
                fileWriter.write(salesman.getId() + ", " + salesman.getName() + ", " +
                        salesman.getSurname() + ", " + salesman.getTotalSales() + "\n");
            }
            System.out.println("Reporte de vendedores generado: SalesmenReport.csv");
        } catch (IOException e) {
            System.out.println("Error al generar reporte de vendedores.");
            e.printStackTrace();
        }
    }

    // Método para generar el reporte de productos
    public void generateProductsReport(Map<Integer, Product> products) {
        try (FileWriter fileWriter = new FileWriter("ProductsReport.csv")) {
            fileWriter.write("ID, Nombre, Precio, CantidadVendida\n");
            for (Product product : products.values()) {
                fileWriter.write(product.getId() + ", " + product.getName() + ", " +
                        product.getPrice() + ", " + product.getQuantitySold() + "\n");
            }
            System.out.println("Reporte de productos generado: ProductsReport.csv");
        } catch (IOException e) {
            System.out.println("Error al generar reporte de productos.");
            e.printStackTrace();
        }
    }
}

package com.proyecto.ventas;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        GenerateInfoFiles generateInfoFiles = new GenerateInfoFiles();

        // Generar archivos de ventas, productos y vendedores
        generateInfoFiles.createSalesMenFile(5, "Juan Perez", 234567890);
        generateInfoFiles.createSalesMenFile(3, "Ana Lopez", 345678901);
        generateInfoFiles.createProductsFile(10);
        generateInfoFiles.createSalesManInfoFile(3);

        // Leer archivos de productos y vendedores
        Map<Long, Salesman> salesmen = generateInfoFiles.readSalesmen();
        Map<Integer, Product> products = generateInfoFiles.readProducts();

        // Procesar ventas
        generateInfoFiles.processSales(salesmen, products, 234567890);
        generateInfoFiles.processSales(salesmen, products, 345678901);

        // Generar reportes
        generateInfoFiles.generateSalesmenReport(salesmen);
        generateInfoFiles.generateProductsReport(products);

        System.out.println("Proceso completado. Revisa los archivos generados.");
    }
}

package com.perfulandia.productos_service;

import com.perfulandia.productos_service.model.*;
import com.perfulandia.productos_service.repository.*;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

public class DataLoader {
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Generar 10 productos ficticios
        for (int i = 0; i < 10; i++) {
            Producto producto = new Producto();
            producto.setNombre(faker.commerce().productName());
            producto.setMarca(faker.company().name());
            producto.setDescripcion(faker.lorem().sentence());
            producto.setPrecio(Double.parseDouble(faker.commerce().price()));
            producto.setStock(random.nextInt(100) + 1);

            productoRepository.save(producto);
        }

        // Listar todos los productos para verificar
        List<Producto> productos = productoRepository.findAll();
        productos.forEach(System.out::println);
    }
}
}

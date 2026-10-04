package net.sofbt.products;

import net.sofbt.products.entities.Product;
import net.sofbt.products.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringMvcThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcThymeleafApplication.class, args);
    }

    // Test de la couche DAO au démarrage + données initiales
    @Bean
    CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder().name("Computer").price(5400).quantity(12).build());
            productRepository.save(Product.builder().name("Printer").price(1200).quantity(5).build());
            productRepository.save(Product.builder().name("Smart Phone").price(3200).quantity(32).build());
            productRepository.save(Product.builder().name("Smart Watch").price(900).quantity(10).build());
            productRepository.save(Product.builder().name("Keyboard").price(250).quantity(40).build());
            productRepository.save(Product.builder().name("Mouse").price(120).quantity(60).build());
            productRepository.save(Product.builder().name("Monitor").price(1800).quantity(8).build());
            productRepository.save(Product.builder().name("Headphones").price(450).quantity(25).build());
            productRepository.save(Product.builder().name("Webcam").price(380).quantity(15).build());
            productRepository.save(Product.builder().name("USB Hub").price(150).quantity(30).build());
            productRepository.save(Product.builder().name("Tablet").price(2700).quantity(9).build());

            System.out.println("--- Tous les produits");
            productRepository.findAll().forEach(System.out::println);
            System.out.println("--- Produit id=1");
            System.out.println(productRepository.findById(1L).orElseThrow());
            System.out.println("--- Produits dont le prix > 2000");
            productRepository.findByPriceGreaterThan(2000).forEach(System.out::println);
        };
    }
}

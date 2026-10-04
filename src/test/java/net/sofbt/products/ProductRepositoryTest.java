package net.sofbt.products;

import net.sofbt.products.entities.Product;
import net.sofbt.products.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void clean() {
        productRepository.deleteAll(); // le CommandLineRunner a déjà inséré des données
    }

    @Test
    void saveFindUpdateDelete() {
        Product saved = productRepository.save(Product.builder().name("Laptop").price(9000).quantity(3).build());
        assertThat(saved.getId()).isNotNull();

        Product found = productRepository.findById(saved.getId()).orElseThrow();
        assertThat(found.getName()).isEqualTo("Laptop");

        found.setPrice(8500);
        productRepository.save(found);
        assertThat(productRepository.findById(saved.getId()).orElseThrow().getPrice()).isEqualTo(8500);

        productRepository.deleteById(saved.getId());
        assertThat(productRepository.findById(saved.getId())).isEmpty();
    }

    @Test
    void searchByNameWithPagination() {
        productRepository.save(Product.builder().name("Smart Phone").price(3200).quantity(5).build());
        productRepository.save(Product.builder().name("Smart Watch").price(900).quantity(5).build());
        productRepository.save(Product.builder().name("Printer").price(1200).quantity(5).build());

        Page<Product> page = productRepository.findByNameContainsIgnoreCase("smart", PageRequest.of(0, 1));
        assertThat(page.getTotalElements()).isEqualTo(2);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.getContent()).hasSize(1);
    }
}

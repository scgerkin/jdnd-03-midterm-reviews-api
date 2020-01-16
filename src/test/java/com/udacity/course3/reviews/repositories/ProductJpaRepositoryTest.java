package com.udacity.course3.reviews.repositories;

import com.udacity.course3.reviews.entities.Product;
import com.udacity.course3.reviews.repositories.jpa.ProductJpaRepository;
import junit.framework.AssertionFailedError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductJpaRepositoryTest {
    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    EntityManager entityManager;
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    ProductJpaRepository productJpaRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(testEntityManager).isNotNull();
        assertThat(productJpaRepository).isNotNull();
    }

    @Test
    public void testCreateProduct() {
        Product expected = productJpaRepository.save(getProduct());
        assertThat(expected).isNotNull();
    }

    @Test
    public void testFindById() {
        Product expected = productJpaRepository.save(getProduct());
        Optional<Product> actual = productJpaRepository.findById(expected.getId());
        if (actual.isPresent()) {
            assertThat(expected.getId()).isEqualTo(actual.get().getId());
        }
        else {
            throw new AssertionFailedError();
        }
    }

    @Test
    public void testListProducts() {
        productJpaRepository.save(getProduct());
        Product secondProduct = new Product();
        secondProduct.setName("asdf");
        productJpaRepository.save(secondProduct);

        List<Product> productList = productJpaRepository.findAll();
        assertThat(productList.size()).isEqualTo(2);
    }

    private Product getProduct() {
        Product product = new Product();
        product.setName("Whatsit");
        product.setDescription("Whosawhatsa");
        return product;
    }
}

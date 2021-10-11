package academy.arima.store.product.repository;

import academy.arima.store.product.entity.Category;
import academy.arima.store.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
}

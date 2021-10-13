package academy.arima.store.customer.repository;

import academy.arima.store.customer.repository.entity.Customer;
import academy.arima.store.customer.repository.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByRegion(Region region);
    Customer findByNumberId(String numberId);
}

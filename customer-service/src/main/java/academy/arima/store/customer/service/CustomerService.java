package academy.arima.store.customer.service;

import academy.arima.store.customer.repository.entity.Customer;
import academy.arima.store.customer.repository.entity.Region;

import java.util.List;

public interface CustomerService {
    List<Customer> findCustomerAll();
    List<Customer> findCustomerByRegion(Region region);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    Customer deleteCustomer(Long id);
    Customer getCustomer(Long id);
}

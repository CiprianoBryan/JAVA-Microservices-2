package academy.arima.store.customer.service;

import academy.arima.store.customer.repository.CustomerRepository;
import academy.arima.store.customer.repository.entity.Customer;
import academy.arima.store.customer.repository.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findCustomerAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findCustomerByRegion(Region region) {
        return customerRepository.findByRegion(region);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer customerDB = customerRepository.findByNumberId(customer.getNumberId());
        if (customerDB != null) {
            return customerDB;
        }
        customer.setStatus("CREATED");
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerDB = getCustomer(customer.getId());
        if (customerDB == null) {
            return null;
        }
        customerDB.setFirstName(customer.getFirstName());
        customerDB.setLastName(customer.getLastName());
        customerDB.setEmail(customer.getEmail());
        customerDB.setPhotoUrl(customer.getPhotoUrl());
        return customerRepository.save(customerDB);
    }

    @Override
    public Customer deleteCustomer(Long id) {
        Customer customer = getCustomer(id);
        if (customer == null) {
            return null;
        }
        customer.setStatus("DELETED");
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
}

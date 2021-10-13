package academy.arima.store.customer.controller;

import academy.arima.store.customer.repository.entity.Customer;
import academy.arima.store.customer.repository.entity.Region;
import academy.arima.store.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> listAllCustomers(@RequestParam(required = false) Long regionId) {
        List<Customer> customers;
        if (regionId != null) {
            customers = customerService.findCustomerByRegion(Region.builder().id(regionId).build());
            if (customers == null) {
                log.error("Customers with Region id {} not found", regionId);
                return ResponseEntity.notFound().build();
            }
        } else {
            customers = customerService.findCustomerAll();
            if (customers == null) {
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCostumer(@PathVariable Long id) {
        log.info("Fetching Customer with id {}", id);
        Customer customer = customerService.getCustomer(id);
        if (customer == null) {
            log.error("Customer with id {} not found", id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
        log.info("Creating Customer: {}", customer);
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage(result));
        }
        Customer customerDB = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDB);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        log.info("Update Customer with id: {}", id);
        customer.setId(id);
        Customer customerDB = customerService.updateCustomer(customer);
        if (customerDB == null) {
            log.error("Customer with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        log.info("Delete Customer with id: {}", id);
        Customer customer = customerService.deleteCustomer(id);
        if (customer == null) {
            log.error("Customer with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream().map(err -> {
            HashMap<String, String> error = new HashMap<>();
            error.put(err.getField(), err.getDefaultMessage());
            return error;
        }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        return errorMessage.toString();
    }
}

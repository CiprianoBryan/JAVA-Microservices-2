package academy.arima.store.shopping.client;

import academy.arima.store.shopping.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "service-product")
@RequestMapping(value = "/products")
public interface ProductClient {
    @GetMapping(value = "/{id}")
    ResponseEntity<Product> getProduct(@PathVariable Long id);

    @PutMapping("/{id}/stock")
    ResponseEntity<Product> updateStockProduct(@PathVariable Long id, @RequestParam Double quantity);
}

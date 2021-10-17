package academy.arima.store.shopping.repository.entity;

import academy.arima.store.shopping.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Data
@AllArgsConstructor
@Builder
@Table(name = "tbl_invoice_items")
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive(message = "El stock debe ser mayor que cero")
    private Double quantity;
    private Double price;
    private Long productId;
    @Transient
    private Double subTotal;

    @Transient
    private Product product;

    public Double getSubTotal() {
        if (price > 0 && quantity > 0) {
            return price*quantity;
        }
        return 0.;
    }

    public InvoiceItem() {
        this.quantity = 0.;
        this.price = 0.;
    }
}

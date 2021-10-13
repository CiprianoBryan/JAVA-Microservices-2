package academy.arima.store.shopping.repository;

import academy.arima.store.shopping.repository.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findByNumberInvoice(String numberInvoice);
}

package academy.arima.store.shopping.service;

import academy.arima.store.shopping.repository.entity.Invoice;

import java.util.List;

public interface InvoiceService {
    List<Invoice> findInvoiceAll();
    Invoice createInvoice(Invoice invoice);
    Invoice updateInvoice(Invoice invoice);
    Invoice deleteInvoice(Long id);
    Invoice getInvoice(Long id);
}

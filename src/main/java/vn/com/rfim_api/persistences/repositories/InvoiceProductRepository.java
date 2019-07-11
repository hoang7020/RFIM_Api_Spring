package vn.com.rfim_api.persistences.repositories;

import vn.com.rfim_api.persistences.entities.InvoiceProduct;

import java.util.List;

public interface InvoiceProductRepository {

    public InvoiceProduct getByInvoiceIdAndProductId(String invoiceId, String productId);

    public void updateProcessQuantity(String invoiceId, String productId, int processQuantity);

    public List<InvoiceProduct> getByInvoiceId(String invoiceId);

}

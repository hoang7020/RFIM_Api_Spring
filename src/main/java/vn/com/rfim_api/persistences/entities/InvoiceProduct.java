package vn.com.rfim_api.persistences.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Invoice_Product")
public class InvoiceProduct {

    @EmbeddedId
    InvoiceProductId invoiceProductId;

    @ManyToOne
    @JoinColumn(name = "invoiceId", nullable = false)
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    private int quantity;

    private int processQuantity;

    public InvoiceProductId getInvoiceProductId() {
        return invoiceProductId;
    }

    public void setInvoiceProductId(InvoiceProductId invoiceProductId) {
        this.invoiceProductId = invoiceProductId;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProcessQuantity() {
        return processQuantity;
    }

    public void setProcessQuantity(int processQuantity) {
        this.processQuantity = processQuantity;
    }
}

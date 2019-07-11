package vn.com.rfim_api.persistences.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Invoice")
public class Invoice {

    @Id
    private String invoiceId;

    private Timestamp date;

    private String description;

    @ManyToOne
    @JoinColumn(name = "statusId", nullable = false)
    private InvoiceStatus invoiceStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "invoice")
    private List<InvoiceProduct> invoiceProducts = new ArrayList<>();


    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public List<InvoiceProduct> getInvoiceProducts() {
        return invoiceProducts;
    }

    public void setInvoiceProducts(List<InvoiceProduct> invoiceProducts) {
        this.invoiceProducts = invoiceProducts;
    }
}

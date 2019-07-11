package vn.com.rfim_api.persistences.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "InvoiceStatus")
public class InvoiceStatus {

    @Id
    private int StatusId;

    private String Status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "invoiceStatus")
    private List<Invoice> invoices = new ArrayList<>();

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int statusId) {
        StatusId = statusId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}

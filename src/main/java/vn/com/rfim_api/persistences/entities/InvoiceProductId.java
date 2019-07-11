package vn.com.rfim_api.persistences.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InvoiceProductId implements Serializable {

    @Column(name = "InvoiceId")
    protected String invoiceId;

    @Column(name = "ProductId")
    protected String productId;

    public InvoiceProductId() {
    }

    public InvoiceProductId(String invoiceId, String productId) {
        this.invoiceId = invoiceId;
        this.productId = productId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InvoiceProductId)) {
            return false;
        }
        InvoiceProductId that = (InvoiceProductId) obj;
        return Objects.equals(getInvoiceId(), that.getInvoiceId()) &&
                Objects.equals(getProductId(), that.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceId(), getProductId());
    }
}

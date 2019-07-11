package vn.com.rfim_api.services.jsonobjects;

import java.util.Date;

public class InvoiceInfoItem {

    private String invoiceId;

    private String productId;

    private String productName;

    private int quantity;

    private int processQuantity;

    private Date date;

    private String shelfId;

    private int Status;

    public InvoiceInfoItem() {
    }

    public InvoiceInfoItem(String invoiceId, String productId, String productName, int quantity, int processQuantity, Date date, int status) {
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.processQuantity = processQuantity;
        this.date = date;
        Status = status;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getShelfId() {
        return shelfId;
    }

    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}

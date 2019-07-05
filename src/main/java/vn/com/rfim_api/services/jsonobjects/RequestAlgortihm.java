package vn.com.rfim_api.services.jsonobjects;

import java.util.List;

public class RequestAlgortihm {

    private List<InvoiceInfoItem> invoiceInfoItems;

    public RequestAlgortihm() {
    }

    public RequestAlgortihm(List<InvoiceInfoItem> invoiceInfoItems) {
        this.invoiceInfoItems = invoiceInfoItems;
    }

    public List<InvoiceInfoItem> getInvoiceInfoItems() {
        return invoiceInfoItems;
    }

    public void setInvoiceInfoItems(List<InvoiceInfoItem> invoiceInfoItems) {
        this.invoiceInfoItems = invoiceInfoItems;
    }
}

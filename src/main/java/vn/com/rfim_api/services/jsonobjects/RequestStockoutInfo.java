package vn.com.rfim_api.services.jsonobjects;

import java.util.List;

public class RequestStockoutInfo {
    private String invoiceId;
    private List<String> boxRfids;

    public RequestStockoutInfo() {
    }

    public RequestStockoutInfo(String invoiceId, List<String> boxRfids) {
        this.invoiceId = invoiceId;
        this.boxRfids = boxRfids;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public List<String> getBoxRfids() {
        return boxRfids;
    }

    public void setBoxRfids(List<String> boxRfids) {
        this.boxRfids = boxRfids;
    }
}

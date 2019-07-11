package vn.com.rfim_api.services.jsonobjects;

import java.util.List;

public class RequestPackageInfo {
    private String invoiceId;
    private String productId;
    private String packageRfid;
    private int invoiceStatus;
    private List<String> boxRfids;

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

    public String getPackageRfid() {
        return packageRfid;
    }

    public void setPackageRfid(String packageRfid) {
        this.packageRfid = packageRfid;
    }

    public int getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(int invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public List<String> getBoxRfids() {
        return boxRfids;
    }

    public void setBoxRfids(List<String> boxRfids) {
        this.boxRfids = boxRfids;
    }
}

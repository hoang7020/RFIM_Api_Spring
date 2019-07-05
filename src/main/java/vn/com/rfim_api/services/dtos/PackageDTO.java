package vn.com.rfim_api.services.dtos;

import java.sql.Timestamp;

public class PackageDTO {


    private String packageRfid;
    private String packageName;
    private int quantity;
    private String productId;
    private String cellId;
    private Timestamp date;

    public PackageDTO() {
    }

    public PackageDTO(String packageRfid, String packageName, int quantity, String productId, String cellId, Timestamp date) {
        this.packageRfid = packageRfid;
        this.packageName = packageName;
        this.quantity = quantity;
        this.productId = productId;
        this.cellId = cellId;
        this.date = date;
    }

    public String getPackageRfid() {
        return packageRfid;
    }

    public void setPackageRfid(String packageRfid) {
        this.packageRfid = packageRfid;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}

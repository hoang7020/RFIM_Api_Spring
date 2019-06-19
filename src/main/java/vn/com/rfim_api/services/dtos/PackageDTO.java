package vn.com.rfim_api.services.dtos;

public class PackageDTO {


    private String packageId;
    private String packageName;
    private int quantity;
    private String productId;
    private String cellId;

    public PackageDTO() {
    }

    public PackageDTO(String packageId, String packageName, int quantity, String productId, String cellId) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.quantity = quantity;
        this.productId = productId;
        this.cellId = cellId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
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
}

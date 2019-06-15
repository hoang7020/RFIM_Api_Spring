package vn.com.rfim_api.services.dtos;

public class PackagedDTO {


    private String packagedId;
    private String packagedName;
    private int quantity;
    private String productId;

    public PackagedDTO() {
    }

    public PackagedDTO(String packagedId, String productId) {
        this.packagedId = packagedId;
        this.productId = productId;
    }


    public String getPackagedId() {
        return packagedId;
    }

    public void setPackagedId(String packagedId) {
        this.packagedId = packagedId;
    }

    public String getPackagedName() {
        return packagedName;
    }

    public void setPackagedName(String packagedName) {
        this.packagedName = packagedName;
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
}

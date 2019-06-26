package vn.com.rfim_api.services.dtos;

public class BoxDTO {

    private String boxRfid;

    private int quantityPerBox;

    private String packageRfid;

    private String productId;

    public BoxDTO() {
    }

    public String getBoxRfid() {
        return boxRfid;
    }

    public void setBoxRfid(String boxRfid) {
        this.boxRfid = boxRfid;
    }

    public int getQuantityPerBox() {
        return quantityPerBox;
    }

    public void setQuantityPerBox(int quantityPerBox) {
        this.quantityPerBox = quantityPerBox;
    }

    public String getPackageRfid() {
        return packageRfid;
    }

    public void setPackageRfid(String packageRfid) {
        this.packageRfid = packageRfid;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}

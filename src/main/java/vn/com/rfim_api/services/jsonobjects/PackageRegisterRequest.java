package vn.com.rfim_api.services.jsonobjects;

import java.util.List;

public class PackageRegisterRequest {
    private String productId;
    private String packageRfid;
    private List<String> boxRfids;

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

    public List<String> getBoxRfids() {
        return boxRfids;
    }

    public void setBoxRfids(List<String> boxRfids) {
        this.boxRfids = boxRfids;
    }
}

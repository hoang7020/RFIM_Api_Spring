package vn.com.rfim_api.services.jsonobjects;

public class SortIssueInfo {
    private String productId;
    private String shelf;

    public SortIssueInfo(String productId, String shelf) {
        this.productId = productId;
        this.shelf = shelf;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }
}

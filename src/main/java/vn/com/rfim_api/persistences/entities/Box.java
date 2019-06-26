package vn.com.rfim_api.persistences.entities;

import javax.persistence.*;

@Entity
@Table(name = "Box")
public class Box {

    @Id
    private String boxRfid;

    @ManyToOne
    @JoinColumn(name = "packageRfid", nullable = true)
    private Package aPackage;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = true)
    private Product product;

    public String getBoxRfid() {
        return boxRfid;
    }

    public void setBoxRfid(String boxRfid) {
        this.boxRfid = boxRfid;
    }

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

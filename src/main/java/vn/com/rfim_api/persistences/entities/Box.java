package vn.com.rfim_api.persistences.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Box")
public class Box {

    @Id
    private String boxRfid;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "packageRfid", nullable = true)
    private Package aPackage;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = true)
    private Product product;

    private Date date;

    public String getBoxRfid() {
        return boxRfid;
    }

    public void setBoxRfid(String boxRfid) {
        this.boxRfid = boxRfid;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

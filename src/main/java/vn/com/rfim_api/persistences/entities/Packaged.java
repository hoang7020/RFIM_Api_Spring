package vn.com.rfim_api.persistences.entities;

import javax.persistence.*;

@Entity
@Table(name = "Packaged")
public class Packaged {

    @Id
    private String packagedId;

    private String packagedName;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @OneToOne
    @JoinColumn(name = "cellId")
    private Cell cell;


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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}

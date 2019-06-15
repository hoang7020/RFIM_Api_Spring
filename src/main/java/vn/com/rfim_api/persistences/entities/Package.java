package vn.com.rfim_api.persistences.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Package")
public class Package {

    @Id
    private String packageId;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = true)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cellId", nullable = true)
    private Cell cell;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aPackage")
    private List<Box> boxes = new ArrayList<>();

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
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

    public List<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(List<Box> boxes) {
        this.boxes = boxes;
    }
}

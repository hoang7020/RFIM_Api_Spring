package vn.com.rfim_api.persistences.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Package")
public class Package {

    @Id
    private String packageRfid;

    private String description;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = true)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cellId", nullable = true)
    private Cell cell;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aPackage", orphanRemoval = true)
    private List<Box> boxes = new ArrayList<>();

    public String getPackageRfid() {
        return packageRfid;
    }

    public void setPackageRfid(String packageRfid) {
        this.packageRfid = packageRfid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

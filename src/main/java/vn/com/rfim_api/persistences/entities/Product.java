package vn.com.rfim_api.persistences.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Product")
public class Product {

    @Id
    private String productId;

    private String productName;

    private String description;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Packaged> listPackaged = new ArrayList<>();

    public List<Packaged> getListPackaged() {
        return listPackaged;
    }

    public void setListPackaged(List<Packaged> listPackaged) {
        this.listPackaged = listPackaged;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}

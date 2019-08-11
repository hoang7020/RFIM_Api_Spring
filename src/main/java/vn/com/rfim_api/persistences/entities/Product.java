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

    private Integer weight;

    private String image;

    private String description;

    private Integer height;

    private Integer width;

    private Integer length;

    private int quantityPerBox;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Package> aPackages = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Box> boxes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<StocktakeHistory> stocktakeHistories = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<InvoiceProduct> invoiceProducts = new ArrayList<>();

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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public int getQuantityPerBox() {
        return quantityPerBox;
    }

    public void setQuantityPerBox(int quantityPerBox) {
        this.quantityPerBox = quantityPerBox;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Package> getaPackages() {
        return aPackages;
    }

    public void setaPackages(List<Package> aPackages) {
        this.aPackages = aPackages;
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(List<Box> boxes) {
        this.boxes = boxes;
    }

    public List<StocktakeHistory> getStocktakeHistories() {
        return stocktakeHistories;
    }

    public void setStocktakeHistories(List<StocktakeHistory> stocktakeHistories) {
        this.stocktakeHistories = stocktakeHistories;
    }

    public List<InvoiceProduct> getInvoiceProducts() {
        return invoiceProducts;
    }

    public void setInvoiceProducts(List<InvoiceProduct> invoiceProducts) {
        this.invoiceProducts = invoiceProducts;
    }
}

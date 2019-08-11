package vn.com.rfim_api.persistences.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "StocktakeHistory")
public class StocktakeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int stocktakeHistoryId;

    private Timestamp date;

    private int quantity;

    private boolean status;

    private String lostBox;

    private String foundBox;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    public int getStocktakeHistoryId() {
        return stocktakeHistoryId;
    }

    public void setStocktakeHistoryId(int stocktakeHistoryId) {
        this.stocktakeHistoryId = stocktakeHistoryId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getLostBox() {
        return lostBox;
    }

    public void setLostBox(String lostBox) {
        this.lostBox = lostBox;
    }

    public String getFoundBox() {
        return foundBox;
    }

    public void setFoundBox(String foundBox) {
        this.foundBox = foundBox;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

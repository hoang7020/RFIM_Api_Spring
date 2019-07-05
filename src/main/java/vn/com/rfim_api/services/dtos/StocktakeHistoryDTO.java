package vn.com.rfim_api.services.dtos;

import java.sql.Timestamp;
import java.util.Date;

public class StocktakeHistoryDTO {

    private int stocktakeHistoryId;

    private int stocktakeTypeId;

    private int userId;

    private String productId;

    private int quantity;

    private Timestamp date;

    private String description;


    public StocktakeHistoryDTO() {
    }

    public int getStocktakeHistoryId() {
        return stocktakeHistoryId;
    }

    public void setStocktakeHistoryId(int stocktakeHistoryId) {
        this.stocktakeHistoryId = stocktakeHistoryId;
    }

    public int getStocktakeTypeId() {
        return stocktakeTypeId;
    }

    public void setStocktakeTypeId(int stocktakeTypeId) {
        this.stocktakeTypeId = stocktakeTypeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

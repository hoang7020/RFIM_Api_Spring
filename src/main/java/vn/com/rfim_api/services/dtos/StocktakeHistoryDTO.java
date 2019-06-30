package vn.com.rfim_api.services.dtos;

import java.sql.Timestamp;
import java.util.Date;

public class StocktakeHistoryDTO {

    private int stocktakeHistoryId;

    private int userId;

    private String productId;

    private int stocktakeTypeId;

    private Timestamp date;


    public StocktakeHistoryDTO() {
    }

    public int getStocktakeHistoryId() {
        return stocktakeHistoryId;
    }

    public void setStocktakeHistoryId(int stocktakeHistoryId) {
        this.stocktakeHistoryId = stocktakeHistoryId;
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

    public int getStocktakeTypeId() {
        return stocktakeTypeId;
    }

    public void setStocktakeTypeId(int stocktakeTypeId) {
        this.stocktakeTypeId = stocktakeTypeId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}

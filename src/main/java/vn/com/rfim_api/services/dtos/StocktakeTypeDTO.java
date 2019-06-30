package vn.com.rfim_api.services.dtos;

public class StocktakeTypeDTO {

    private int stocktakeTypeId;

    private String stocktakeType;

    public StocktakeTypeDTO() {
    }

    public int getStocktakeTypeId() {
        return stocktakeTypeId;
    }

    public void setStocktakeTypeId(int stocktakeTypeId) {
        this.stocktakeTypeId = stocktakeTypeId;
    }

    public String getStocktakeType() {
        return stocktakeType;
    }

    public void setStocktakeType(String stocktakeType) {
        this.stocktakeType = stocktakeType;
    }
}

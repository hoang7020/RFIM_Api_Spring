package vn.com.rfim_api.persistences.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "StocktakeType")
public class StocktakeType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int stocktakeTypeId;

    private String stocktaketype;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stocktakeType")
    private List<StocktakeHistory> stocktakeTypes = new ArrayList<>();

    public int getStocktakeTypeId() {
        return stocktakeTypeId;
    }

    public void setStocktakeTypeId(int stocktakeTypeId) {
        this.stocktakeTypeId = stocktakeTypeId;
    }

    public String getStocktaketype() {
        return stocktaketype;
    }

    public void setStocktaketype(String stocktaketype) {
        this.stocktaketype = stocktaketype;
    }

    public List<StocktakeHistory> getStocktakeTypes() {
        return stocktakeTypes;
    }

    public void setStocktakeTypes(List<StocktakeHistory> stocktakeTypes) {
        this.stocktakeTypes = stocktakeTypes;
    }
}

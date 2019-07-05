package vn.com.rfim_api.persistences.repositories;

import java.sql.Timestamp;
import java.util.Date;

public interface StocktakeHistoryRepository {

    public boolean addStocktakeHistory(int stocktakeTypeId, int userId, String productId,
                                       int quantity, Timestamp date, String description);
}

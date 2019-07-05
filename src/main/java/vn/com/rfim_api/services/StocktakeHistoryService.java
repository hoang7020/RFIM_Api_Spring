package vn.com.rfim_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.repositories.StocktakeHistoryRepository;
import vn.com.rfim_api.services.jsonobjects.ResponseMesasge;

import java.sql.Timestamp;

@Service
@Transactional
public class StocktakeHistoryService {

    @Autowired
    private StocktakeHistoryRepository context;

    //Create stocktake history
    public ResponseEntity addStocktakeHistory(int stocktakeTypeId, int userId, String productId,
                                              int quantity, Timestamp date, String description) {
        ResponseMesasge response = new ResponseMesasge();
        boolean result = context.addStocktakeHistory(stocktakeTypeId, userId, productId, quantity, date, description);
        if (result) {
            response.setMessage("Report sccessfully.");
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            response.setMessage("Report fail.");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

}

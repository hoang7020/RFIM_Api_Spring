package vn.com.rfim_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.com.rfim_api.services.StocktakeHistoryService;
import vn.com.rfim_api.services.dtos.StocktakeHistoryDTO;

@RestController
public class StocktakeHistoryController {

    @Autowired
    StocktakeHistoryService service;

    //Create stocktake history
    @PostMapping(value = "/stocktake_histories")
    public ResponseEntity addStocktakeHistory(@RequestBody StocktakeHistoryDTO request) {
        return service.addStocktakeHistory(request.getUserId(), request.getProductId(),
                request.getQuantity(), request.getDate(), request.getDescription());
    }

}

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
    @PostMapping(name = "stocktakehistories")
    public ResponseEntity addStocktakeHistory(@RequestBody StocktakeHistoryDTO request) {
//        return ResponseEntity.ok(request.getStocktakeTypeId());
        return service.addStocktakeHistory(request.getUserId(), request.getProductId(), request.getDate(), request.getStocktakeTypeId());
    }

}

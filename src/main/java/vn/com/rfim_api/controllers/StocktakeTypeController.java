package vn.com.rfim_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.rfim_api.services.StocktakeTypeService;

@RestController
public class StocktakeTypeController {

    @Autowired
    private StocktakeTypeService service;
//
//    //Get all stocktake type
//    @GetMapping(value = "/stocktaketypes/")
//    public ResponseEntity getAllStocktakeType() {
//        return service.getAllStocktakeType();
//    }

}

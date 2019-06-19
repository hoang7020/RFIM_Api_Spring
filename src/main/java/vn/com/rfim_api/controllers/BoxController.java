package vn.com.rfim_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vn.com.rfim_api.services.BoxService;
import vn.com.rfim_api.services.dtos.BoxDTO;

@Controller
public class BoxController {

    @Autowired
    private BoxService service;

    @PostMapping(value = "/boxes/stockout")
    public ResponseEntity stockOutBox(@RequestBody BoxDTO boxDTO) {
        return service.stockOutBox(boxDTO.getBoxId());
    }

}

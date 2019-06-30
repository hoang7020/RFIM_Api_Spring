package vn.com.rfim_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vn.com.rfim_api.services.BoxService;

@RestController
public class BoxController {

    @Autowired
    private BoxService service;

    @GetMapping(value = "/boxes/products/{id}")
    public ResponseEntity getBoxRfidsByProductId(@PathVariable("id") String id) {
        return service.getBoxRfidsByProductId(id);
    }

}

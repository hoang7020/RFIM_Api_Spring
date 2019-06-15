package vn.com.rfim_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.com.rfim_api.services.PackagedService;
import vn.com.rfim_api.services.dtos.PackagedDTO;

@RestController
public class PackagedController {

    @Autowired
    private PackagedService service;

    //Create new packaged by using rfid id and map with product id
    @PostMapping(value = "/packageds")
    public ResponseEntity registerPackage(@RequestBody PackagedDTO packaged) {
        return service.registerPackage(packaged.getPackagedId(), packaged.getProductId());
    }
}

package vn.com.rfim_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.com.rfim_api.services.PackageService;
import vn.com.rfim_api.services.dtos.PackageDTO;

@RestController
public class PackageController {

    @Autowired
    private PackageService service;

    //Create new packaged by using rfid id and map with product id
    @PostMapping(value = "/packageds/register")
    public ResponseEntity registerPackage(@RequestBody PackageDTO packaged) {
        return service.registerPackage(packaged.getPackagedId(), packaged.getProductId());
    }
}

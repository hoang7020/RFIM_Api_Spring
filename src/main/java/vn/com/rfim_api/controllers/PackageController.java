package vn.com.rfim_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vn.com.rfim_api.persistences.entities.Package;
import vn.com.rfim_api.services.PackageService;
import vn.com.rfim_api.services.dtos.PackageDTO;
import vn.com.rfim_api.services.jsonobjects.PackageRegisterRequest;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

@RestController
public class PackageController {

    @Autowired
    private PackageService service;

    //Create new box
    //Crete new package
    //Map box with package
    //Map package with product
    @PostMapping(value = "/packages/register")
    public ResponseEntity registerPackage(@RequestBody PackageRegisterRequest request) {
        return service.registerPackage(request.getPackageRfid(), request.getProductId(), request.getBoxRfids());
    }

    //Stock in Package by mapping package with cell id
    @PostMapping(value = "/packages/stockin")
    public ResponseEntity stockIn(@RequestBody PackageDTO pac) {
        return service.stockIn(pac.getPackageRfid(), pac.getCellId());
    }

    //Stock out box by remove box
    @PostMapping(value = "/packages/stockout")
    public ResponseEntity stockOut(@RequestBody PackageRegisterRequest request) {
        return service.stockOut(request.getBoxRfids());
    }
}

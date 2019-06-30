package vn.com.rfim_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.rfim_api.services.PackageService;
import vn.com.rfim_api.services.dtos.PackageDTO;
import vn.com.rfim_api.services.jsonobjects.RequestPackageInfo;

@RestController
public class PackageController {

    @Autowired
    private PackageService service;

    //Create new box
    //Crete new package
    //Map box with package
    //Map package with product
    @PostMapping(value = "/packages/register")
    public ResponseEntity registerPackage(@RequestBody RequestPackageInfo request) {
        return service.registerPackage(request.getPackageRfid(), request.getProductId(), request.getBoxRfids());
    }

    //Get package by package rfid
    @GetMapping(value = "/packages/package_rfid/{id}")
    public ResponseEntity getPackageByPackageRfid(@PathVariable("id") String id) {
        return service.getPackageByPackageRfid(id);
    }

    //Stock in Package by mapping package with cell id
    @PostMapping(value = "/packages/stockin")
    public ResponseEntity stockIn(@RequestBody PackageDTO pac) {
        return service.stockIn(pac.getPackageRfid(), pac.getCellId());
    }

    //Stock out box by remove box
    @PostMapping(value = "/packages/stockout")
    public ResponseEntity stockOut(@RequestBody RequestPackageInfo request) {
        return service.stockOut(request.getBoxRfids());
    }

    //Transfer package by update cell rfid
    @PostMapping(value = "/packages/transfer_package")
    public ResponseEntity transferPackages(@RequestBody PackageDTO pac) {
        return service.transferPackage(pac.getPackageRfid(), pac.getCellId());
    }

    //Transfer box between packaged by update package rfid
    @PostMapping(value = "/packages/transfer_boxes")
    public ResponseEntity transferBoxes(@RequestBody RequestPackageInfo request) {
        System.out.println(request.getPackageRfid());
        return service.transferBoxes(request.getPackageRfid(), request.getBoxRfids());
    }
}

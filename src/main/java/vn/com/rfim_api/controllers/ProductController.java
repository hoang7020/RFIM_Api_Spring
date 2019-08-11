package vn.com.rfim_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vn.com.rfim_api.services.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    //Get all products
    @GetMapping(value = "/products")
    public ResponseEntity getAllProduct() {
        return service.getAll();
    }

    //Get product by box rfid
    //Check box info
    //Check box is missing
    @GetMapping(value = "/products/boxes/{id}")
    public ResponseEntity checkBoxInfo(@PathVariable("id") String id) {
        return service.checkBoxInfo(id);
    }

}

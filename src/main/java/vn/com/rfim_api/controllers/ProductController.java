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

    //Get products by category id
    @GetMapping(value = "/products/categories/{id}")
    public ResponseEntity getProductsByCategoryId(@PathVariable String id) {
        return service.getProductsByCategoryId(id);
    }

    //Get product by box id
    @GetMapping(value = "/products/boxes/{id}")
    public ResponseEntity getProudctByBoxId(@PathVariable("id") String id) {
        return service.getProductByBoxId(id);
    }

}

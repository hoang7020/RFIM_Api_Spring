package vn.com.rfim_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.rfim_api.services.CategoryService;

@RestController
public class CategoryControler {

    @Autowired
    private CategoryService service;

    //Get all categories
    @GetMapping(value = "/categories")
    public ResponseEntity getAllCategories() {
        return service.getAll();
    }

}

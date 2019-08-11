package vn.com.rfim_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.rfim_api.services.ShelfService;
import vn.com.rfim_api.utils.PropertiesUtil;

@RestController
public class ShelfController {

    @Autowired
    private ShelfService service;

    //Get all shelves
    @GetMapping(value = "/shelves")
    public ResponseEntity getAllShelves() {
        return service.getAll();
    }

    //Get shelves by floor id
    @GetMapping(value = "/shelves/floors/{id}")
    public ResponseEntity getShelfByCellId(@PathVariable("id") String id) {
        return service.getShelfByFloorId(id);
    }

}

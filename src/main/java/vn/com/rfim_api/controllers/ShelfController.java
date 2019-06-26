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

    @RequestMapping(value = "/shelves", method = RequestMethod.GET)
    public ResponseEntity getAllShelves() {
        return service.getAll();
    }

    @GetMapping(value = "/shelves/floors/{id}")
    public ResponseEntity getShelfByCellId(@PathVariable("id") String id) {
        return service.getShelfByFloorId(id);
    }

    @GetMapping(value = "/test")
    public ResponseEntity test() {
        return new ResponseEntity(PropertiesUtil.getString("no_shelf_found"), HttpStatus.OK);
    }
}

package vn.com.rfim_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vn.com.rfim_api.services.FloorService;
import vn.com.rfim_api.services.dtos.FloorDTO;

import java.util.List;

@RestController
public class FloorController {

    @Autowired
    private FloorService service;

    //get floors by shelf id
    @GetMapping(value = "/floor/{id}")
    public ResponseEntity getFloorByShelfId(@PathVariable("id") String id) {
        return service.getFloorByShelfId(id);
    }

    //get floor by cell id
    @GetMapping(value = "/floors/cells/{id}")
    public ResponseEntity getFloorByCellId(@PathVariable("id") String id) {
        return service.getFloorByCellId(id);
    }
}

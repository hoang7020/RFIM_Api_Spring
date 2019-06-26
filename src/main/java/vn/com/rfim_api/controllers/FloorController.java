package vn.com.rfim_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vn.com.rfim_api.services.FloorService;
import vn.com.rfim_api.services.dtos.FloorDTO;
import vn.com.rfim_api.services.jsonobjects.ResultResponse;

import java.util.List;

@RestController
public class FloorController {

    @Autowired
    private FloorService service;

    //Get all floors
    @GetMapping(value = "/floors")
    public ResponseEntity getAllFloors() {
        ResultResponse response = new ResultResponse();
        List<FloorDTO> floors = service.getAll();
        if (floors.size() > 0) {
            return new ResponseEntity(floors, HttpStatus.OK);
        } else {
            response.setMessage("No floor found");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    //get floors by shelf id
    @GetMapping(value = "/floors/{id}")
    public ResponseEntity getFloorByShelfId(@PathVariable("id") String id) {
        return service.getFloorByShelfId(id);
    }

    //get floor by cell id
    @GetMapping(value = "/floors/cells/{id}")
    public ResponseEntity getFloorByCellId(@PathVariable("id") String id) {
        return service.getFloorByCellId(id);
    }
}

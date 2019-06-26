package vn.com.rfim_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.rfim_api.services.CellService;
import vn.com.rfim_api.services.dtos.CellDTO;

@RestController
public class CellController {

    @Autowired
    private CellService service;

    //Get cell by floor id
    @GetMapping(value = "/cells/floors/{id}")
    public ResponseEntity getCellByFloorId(@PathVariable("id") String id) {
        return service.getByFloorId(id);
    }

    //Register cell with rfid tag
    @PostMapping(value = "/cells/register")
    public ResponseEntity registerCell(@RequestBody CellDTO cell) {
        return service.registerCell(cell.getCellId(), cell.getCellRfid());
    }

    //Get cell by cell rfid
    @GetMapping(value = "/cells/rfid/{id}")
    public ResponseEntity getCellByCellRfid(@PathVariable("id") String id) {
        return service.getCellByCellRfid(id);
    }
}

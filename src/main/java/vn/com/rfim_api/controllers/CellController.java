package vn.com.rfim_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.rfim_api.services.CellService;
import vn.com.rfim_api.services.dtos.CellDTO;
import vn.com.rfim_api.services.response.CellData;
import vn.com.rfim_api.services.response.FloorData;
import vn.com.rfim_api.services.response.ResultResponse;

import java.util.List;

@RestController
public class CellController {

    @Autowired
    private CellService service;

    @GetMapping(value = "/cells/{id}")
    public ResponseEntity getByFloorId(@PathVariable("id") String id) {
        return service.getByFloorId(id);
    }

    @PostMapping(value = "/cells")
    public ResponseEntity registerCell(@RequestBody CellDTO cell) {
        return service.registerCell(cell.getCellId(), cell.getCellRfid());
    }

}

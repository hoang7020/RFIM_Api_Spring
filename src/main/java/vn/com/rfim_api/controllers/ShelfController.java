package vn.com.rfim_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.com.rfim_api.services.ShelfService;
import vn.com.rfim_api.services.dtos.ShelfDTO;
import vn.com.rfim_api.services.jsonobjects.ResultResponse;
import vn.com.rfim_api.services.jsonobjects.ShelfData;

import java.util.List;

@RestController
public class ShelfController {

    @Autowired
    private ShelfService service;

    @RequestMapping(value = "/shelves", method = RequestMethod.GET)
    public ResponseEntity getAllShelves() {
        ResultResponse response = new ResultResponse();
        List<ShelfDTO> shelves = service.getAll();
        if (shelves.size() > 0) {
            response.setMessage("Ok");
            response.setData(new ShelfData(shelves));
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            response.setMessage("No shelf found");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public ResponseEntity demo() {
        return new ResponseEntity("Hello World!", HttpStatus.OK);
    }
}

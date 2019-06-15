package vn.com.rfim_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vn.com.rfim_api.services.BoxService;
import vn.com.rfim_api.services.dtos.BoxDTO;

@Controller
public class BoxController {

    @Autowired
    private BoxService service;

    //Create new box by using rfid id and map with package
    @PostMapping(value = "/boxes/register")
    public ResponseEntity registerBox(@RequestBody BoxDTO box) {
        return service.addBox(box.getBoxId(), box.getPackageId());
    }

}

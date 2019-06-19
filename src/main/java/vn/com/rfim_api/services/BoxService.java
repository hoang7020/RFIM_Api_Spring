package vn.com.rfim_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.com.rfim_api.persistences.repositories.BoxRepository;
import vn.com.rfim_api.services.jsonobjects.ResultResponse;

@Service
public class BoxService {

    @Autowired
    private BoxRepository context;

    public ResponseEntity stockOutBox(String boxId) {
        ResultResponse response = new ResultResponse();
        if (context.isExit(boxId)) {
            context.deleteBox(boxId);
            response.setMessage("Stock Out Box Successfull.");
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            response.setMessage("No Box Found.");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }

}

package vn.com.rfim_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.com.rfim_api.persistences.repositories.BoxRepository;
import vn.com.rfim_api.services.response.ResultResponse;

@Service
public class BoxService {

    @Autowired
    private BoxRepository context;

    //Create new box and map with package
    public ResponseEntity addBox(String boxId, String packageId) {
        ResultResponse response = new ResultResponse();
        boolean result = context.addBox(boxId, packageId);
        if (result) {
            response.setMessage("Register Box Successful!");
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            response.setMessage("Register Box Fail!");
            return new ResponseEntity(response, HttpStatus.NOT_ACCEPTABLE);
        }
    }

}

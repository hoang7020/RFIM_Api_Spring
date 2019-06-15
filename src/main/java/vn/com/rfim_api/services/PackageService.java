package vn.com.rfim_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.repositories.PackageRepository;
import vn.com.rfim_api.services.response.ResultResponse;

@Service
@Transactional
public class PackageService {

    @Autowired
    private PackageRepository context;

    //Create new packaged by using rfid id and map with product id
    public ResponseEntity registerPackage(String packageId, String productId) {
        ResultResponse response = new ResultResponse();
        boolean result = context.registerPackage(packageId, productId);
        if (result) {
            response.setMessage("Register Package Successful!");
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            response.setMessage("Register Package Fail!");
            return new ResponseEntity(response, HttpStatus.NOT_ACCEPTABLE);
        }
    }

}

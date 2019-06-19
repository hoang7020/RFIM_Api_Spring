package vn.com.rfim_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.repositories.BoxRepository;
import vn.com.rfim_api.persistences.repositories.PackageRepository;
import vn.com.rfim_api.services.jsonobjects.ResultResponse;

import java.util.List;

@Service
@Transactional
public class PackageService {

    @Autowired
    private PackageRepository packgeContext;
    @Autowired
    private BoxRepository boxContext;

    //Create new packaged by using rfid id and map with product id
    public ResponseEntity registerPackage(String packageId, String productId, List<String> productRfids) {
        ResultResponse response = new ResultResponse();
        packgeContext.addPackage(packageId, productId);
        boxContext.addBatchBox(productRfids, packageId);
        response.setMessage("Register Successful!");
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //Map package with cell id
    public ResponseEntity updatePackageCellId(String packageId, String cellId) {
        ResultResponse response = new ResultResponse();
        boolean result = packgeContext.updatePackageCellId(packageId, cellId);
        if (result) {
            response.setMessage("Stock In Package Successfull.");
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            response.setMessage("Stcok In Package Fail.");
            return new ResponseEntity(response, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}

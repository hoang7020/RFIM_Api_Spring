package vn.com.rfim_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.repositories.BoxRepository;
import vn.com.rfim_api.persistences.repositories.PackageRepository;
import vn.com.rfim_api.services.jsonobjects.ResponseMesasge;
import vn.com.rfim_api.services.jsonobjects.ResultResponse;
import vn.com.rfim_api.utils.PropertiesUtil;

import java.util.List;

@Service
@Transactional
public class PackageService {

    @Autowired
    private PackageRepository packageContext;
    @Autowired
    private BoxRepository boxContext;

    //Create new packaged by using rfid id and map with product id
    public ResponseEntity registerPackage(String packageId, String productId, List<String> boxRfids) {
        ResponseMesasge response = new ResponseMesasge();
        if (!packageContext.isExit(packageId)) {
            packageContext.addPackage(packageId, productId);
        }
        boxContext.addBatchBox(boxRfids, packageId, productId);
        response.setMessage(PropertiesUtil.getString("register_package_successfully"));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //Map package with cell id
    //Stock in package
    public ResponseEntity stockIn(String packageRfid, String cellId) {
        ResponseMesasge response = new ResponseMesasge();
        if (packageContext.isExit(packageRfid)) {
            if (!packageContext.isStockIn(packageRfid)) {
                boolean result = packageContext.updatePackageCellId(packageRfid, cellId);
                if (result) {
                    response.setMessage(PropertiesUtil.getString("stock_in_package_successfully"));
                    return new ResponseEntity(response, HttpStatus.OK);
                } else {
                    response.setMessage(PropertiesUtil.getString("stock_in_package_fail"));
                    return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                response.setMessage(PropertiesUtil.getString("package_already_stocked_in"));
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
        } else {
            response.setMessage(PropertiesUtil.getString("package_not_exit"));
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    //Remove box with box rifd
    //Stock out box
    public ResponseEntity stockOut(List<String> boxRfids) {
        ResponseMesasge response = new ResponseMesasge();
        List<String> listPackageIds = boxContext.deleteBatchBox(boxRfids);
        for (String id : listPackageIds) {
            if (packageContext.isEmpty(id)) {
                packageContext.deletePackage(id);
            }
        }
        response.setMessage(PropertiesUtil.getString("stock_out_package_succesfully"));
        return new ResponseEntity(HttpStatus.OK);
    }

    //Update package cell id
    //Transfer package
    public ResponseEntity transferPackage(String packageRfid, String cellId) {
        ResponseMesasge response = new ResponseMesasge();
        boolean result = packageContext.updatePackageCellId(packageRfid, cellId);
        if (result) {
            response.setMessage(PropertiesUtil.getString("stock_out_package_succesfully"));
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            response.setMessage(PropertiesUtil.getString("stock_in_package_fail"));
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }
}

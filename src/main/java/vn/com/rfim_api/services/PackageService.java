package vn.com.rfim_api.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.constants.Constant;
import vn.com.rfim_api.persistences.entities.Package;
import vn.com.rfim_api.persistences.repositories.BoxRepository;
import vn.com.rfim_api.persistences.repositories.PackageRepository;
import vn.com.rfim_api.services.dtos.PackageDTO;
import vn.com.rfim_api.services.jsonobjects.ResponseMesasge;

import java.util.List;

@Service
@Transactional
public class PackageService {

    @Autowired
    private PackageRepository packageContext;
    @Autowired
    private BoxRepository boxContext;
    @Autowired
    private ModelMapper mapper;

    //Create new packaged by using rfid id and map with product id
    public ResponseEntity registerPackage(String packageId, String productId, List<String> boxRfids) {
        ResponseMesasge response = new ResponseMesasge();
        if (!packageContext.isExit(packageId)) {
            packageContext.addPackage(packageId, productId);
        }
        boxContext.addBatchBox(boxRfids, packageId, productId);
        response.setMessage(Constant.REGISTER_PACKAGE_SUCCESSFULLY);
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
                    response.setMessage(Constant.STOCK_IN_PACKAGE_SUCCESSFULLY);
                    return new ResponseEntity(response, HttpStatus.OK);
                } else {
                    response.setMessage(Constant.STOCK_IN_PACKAGE_FAIL);
                    return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                response.setMessage(Constant.PACKAGE_ALREADY_STOKED_IN);
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
        } else {
            response.setMessage(Constant.PACKAGE_NOT_EXIT);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    //Check package is registerd or not
    public ResponseEntity getPackageByPackageRfid(String packageRfid) {
        ResponseMesasge response = new ResponseMesasge();
        Package pac = packageContext.getByPackageRfid(packageRfid);
        if (pac != null) {
            PackageDTO packageDTO = mapper.map(pac, PackageDTO.class);
            return new ResponseEntity(packageDTO, HttpStatus.OK);
        } else {
            response.setMessage(Constant.PACKAGE_NOT_EXIT);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    //Remove box with box rifd
    //Stock out box
    public ResponseEntity stockOut(List<String> boxRfids) {
        ResponseMesasge response = new ResponseMesasge();
        List<String> listPackageRfids = boxContext.deleteBatchBox(boxRfids);
        for (String id : listPackageRfids) {
            if (packageContext.isEmpty(id)) {
                packageContext.deletePackage(id);
            }
        }
        response.setMessage(Constant.STOCK_OUT_PACKAGE_SUCCESSFULLY);
        return new ResponseEntity(HttpStatus.OK);
    }

    //Update package cell id
    //Transfer package
    public ResponseEntity transferPackage(String packageRfid, String cellId) {
        ResponseMesasge response = new ResponseMesasge();
        boolean result = packageContext.updatePackageCellId(packageRfid, cellId);
        if (result) {
            response.setMessage(Constant.TRANSFER_PACKAGE_SUCCESSFULLY);
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            response.setMessage(Constant.TRANSFER_PACKAGE_FAIL);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    //Update package rfid of box
    //Transfer box between package
    public ResponseEntity transferBoxes(String packageRfid, List<String> boxRfids) {
        ResponseMesasge response = new ResponseMesasge();
        List<String> listPackageRfids = boxContext.updateBatchBoxPackageRfid(packageRfid, boxRfids);
        for (String id : listPackageRfids) {
            if (packageContext.isEmpty(id)) {
                packageContext.deletePackage(id);
            }
        }
        response.setMessage(Constant.TRANSFER_BOX_SUCCESSFULLY);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}

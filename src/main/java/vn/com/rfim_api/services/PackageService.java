package vn.com.rfim_api.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.constants.Constant;
import vn.com.rfim_api.persistences.entities.InvoiceProduct;
import vn.com.rfim_api.persistences.entities.Package;
import vn.com.rfim_api.persistences.repositories.BoxRepository;
import vn.com.rfim_api.persistences.repositories.InvoiceProductRepository;
import vn.com.rfim_api.persistences.repositories.InvoiceRepository;
import vn.com.rfim_api.persistences.repositories.PackageRepository;
import vn.com.rfim_api.services.dtos.PackageDTO;
import vn.com.rfim_api.services.jsonobjects.ResponseMesasge;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class PackageService {

    @Autowired
    private PackageRepository packageContext;
    @Autowired
    private BoxRepository boxContext;
    @Autowired
    private InvoiceRepository invoiceContext;
    @Autowired
    private InvoiceProductRepository invoiceProductContext;
    @Autowired
    private ModelMapper mapper;

    //Create new packaged by using rfid id and map with product id
    public ResponseEntity registerPackage(String invoiceId, String packageId, String productId, int invoiceStatus,  List<String> boxRfids) {
        ResponseMesasge response = new ResponseMesasge();
        if (!packageContext.isExit(packageId)) {
            packageContext.addPackage(packageId, productId);
        }
        boxContext.addBatchBox(boxRfids, packageId, productId);
        InvoiceProduct invoiceProduct = invoiceProductContext.getByInvoiceIdAndProductId(invoiceId, productId);
        if (invoiceProduct.getProcessQuantity() < invoiceProduct.getQuantity()) {
            if (invoiceStatus == 1) {
                invoiceContext.updateInvoiceStatus(invoiceId, 2);
            }
            invoiceProductContext.updateProcessQuantity(invoiceId, productId, invoiceProduct.getProcessQuantity() + boxRfids.size());
        }
        if (isReceiptInvoiceCompleted(invoiceId)) {
            invoiceContext.updateInvoiceStatus(invoiceId, 3);
        }
        response.setMessage(Constant.REGISTER_PACKAGE_SUCCESSFULLY);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //Map package with cell id
    //Stock in package
    public ResponseEntity stockIn(String packageRfid, String cellId, Timestamp date) {
        ResponseMesasge response = new ResponseMesasge();
        if (packageContext.isExit(packageRfid)) {
            if (!packageContext.isStockIn(packageRfid)) {
                boolean result = packageContext.stockinPackage(packageRfid, cellId, date);
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
    public ResponseEntity stockOut(List<String> boxRfids, String invoiceId) {
        ResponseMesasge response = new ResponseMesasge();
        List<String> listPackageRfids = boxContext.deleteBatchBox(boxRfids);
        for (String id : listPackageRfids) {
            if (packageContext.isEmpty(id)) {
                packageContext.deletePackage(id);
            }
        }
        invoiceContext.updateInvoiceStatus(invoiceId, 3);
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

    private boolean isReceiptInvoiceCompleted(String invoiceId) {
        List<InvoiceProduct> invoiceProducts = invoiceProductContext.getByInvoiceId(invoiceId);
        int sumQuantity = 0;
        int sumProcessQuantity = 0;
        for (InvoiceProduct ip: invoiceProducts) {
            sumQuantity += ip.getQuantity();
            sumProcessQuantity += ip.getProcessQuantity();
        }
        System.out.println("Q: " + sumQuantity + " PQ: " + sumProcessQuantity);
        if (sumQuantity == sumProcessQuantity) {
            return true;
        } else {
            return false;
        }
    }

}

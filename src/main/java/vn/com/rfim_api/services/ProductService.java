package vn.com.rfim_api.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.constants.Constant;
import vn.com.rfim_api.persistences.entities.Box;
import vn.com.rfim_api.persistences.entities.Product;
import vn.com.rfim_api.persistences.repositories.BoxRepository;
import vn.com.rfim_api.persistences.repositories.ProductRepository;
import vn.com.rfim_api.services.dtos.ProductDTO;
import vn.com.rfim_api.services.jsonobjects.ResponseMesasge;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productContext;
    @Autowired
    private BoxRepository boxContext;
    @Autowired
    private ModelMapper mapper;

    //Get all products
    public ResponseEntity getAll() {
        List<ProductDTO> products = mapper.map(productContext.getAll(), new TypeToken<List<ProductDTO>>() {
        }.getType());
        if (products.size() > 0) {
            return new ResponseEntity(products, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //Get product by box rfid
    //Check box info
    //Check box is missing
    public ResponseEntity checkBoxInfo(String boxRfid) {
        ResponseMesasge response = new ResponseMesasge();
        Box box = boxContext.isMissing(boxRfid);
        if (box != null) {
            if (box.isStatus()) {
                Product result = productContext.getByBoxRfid(boxRfid);
                if (result != null) {
                    ProductDTO product = mapper.map(result, ProductDTO.class);
                    if (product != null) {
                        return new ResponseEntity(product, HttpStatus.OK);
                    } else {
                        response.setMessage(Constant.BOX_NOT_EXIT);
                        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                    }
                } else {
                    response.setMessage(Constant.BOX_NOT_EXIT);
                    return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                response.setMessage(Constant.MISSING_BOX);
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
        } else {
            response.setMessage(Constant.BOX_NOT_EXIT);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }
}

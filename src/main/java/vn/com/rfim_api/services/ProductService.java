package vn.com.rfim_api.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.constants.Constant;
import vn.com.rfim_api.persistences.entities.Product;
import vn.com.rfim_api.persistences.repositories.ProductRepository;
import vn.com.rfim_api.services.dtos.ProductDTO;
import vn.com.rfim_api.services.jsonobjects.ProductData;
import vn.com.rfim_api.services.jsonobjects.ResponseMesasge;
import vn.com.rfim_api.services.jsonobjects.ResultResponse;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository context;
    @Autowired
    private ModelMapper mapper;

    //Get all products
    public ResponseEntity getAll() {
        ResultResponse response = new ResultResponse();
        List<ProductDTO> products = mapper.map(context.getAll(), new TypeToken<List<ProductDTO>>() {
        }.getType());
        if (products.size() > 0) {
            return new ResponseEntity(products, HttpStatus.OK);
        } else {
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

//    //Get product by category id
//    public ResponseEntity getProductsByCategoryId(String categoryId) {
//        ResultResponse response = new ResultResponse();
//        List<ProductDTO> products = mapper.map(context.getByCategoryId(categoryId), new TypeToken<List<ProductDTO>>() {
//        }.getType());
//        if (products.size() > 0) {
//            response.setData(new ProductData(products));
//            return new ResponseEntity(response, HttpStatus.OK);
//        } else {
//            response.setMessage("No Product Found!");
//            return new ResponseEntity(response, HttpStatus.NO_CONTENT);
//        }
//    }

    //Get product by box rfid
    public ResponseEntity getProductByBoxRfid(String boxRfid) {
        ResponseMesasge response = new ResponseMesasge();
        Product result = context.getByBoxRfid(boxRfid);
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
    }
}

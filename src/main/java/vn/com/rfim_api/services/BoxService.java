package vn.com.rfim_api.services;

import org.modelmapper.ModelMapper;
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
public class BoxService {

    @Autowired
    private BoxRepository boxContext;

    //Get all box rfids by product id
    public ResponseEntity getBoxRfidsByProductId(String productId) {
        List<String> boxRfids = boxContext.getByProductId(productId);
        if (boxRfids.size() > 0) {
            return new ResponseEntity(boxRfids, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}

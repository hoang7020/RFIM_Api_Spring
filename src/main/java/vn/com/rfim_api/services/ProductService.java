package vn.com.rfim_api.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.repositories.ProductRepository;
import vn.com.rfim_api.services.dtos.ProductDTO;
import vn.com.rfim_api.services.response.CategoryData;
import vn.com.rfim_api.services.response.CellData;
import vn.com.rfim_api.services.response.ResultResponse;

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
        List<ProductDTO> products = mapper.map(context.getAll(), new TypeToken<List<ProductDTO>>(){}.getType());
        if (products.size() > 0) {
            response.setMessage("OK");
            response.setData(new CategoryData(products));
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            response.setMessage("No Category Found!");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }

    //Get product by category id
    public ResponseEntity getByCategoryId(String categoryId) {
        ResultResponse response = new ResultResponse();
        List<ProductDTO> products = mapper.map(context.getByCategoryId(categoryId), new TypeToken<List<ProductDTO>>(){}.getType());
        if (products.size() > 0) {
            response.setMessage("OK");
            response.setData(new CellData(products));
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            response.setMessage("No Proudct Found!");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }

}

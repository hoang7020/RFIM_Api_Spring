package vn.com.rfim_api.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.repositories.CategoryRepository;
import vn.com.rfim_api.services.dtos.CategoryDTO;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository context;
    @Autowired
    private ModelMapper mapper;

    //Get all categories
    public ResponseEntity getAll() {
        List<CategoryDTO> categories = mapper.map(context.getAll(), new TypeToken<List<CategoryDTO>>(){}.getType());
        if (categories.size() > 0) {
            return new ResponseEntity(categories, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

}

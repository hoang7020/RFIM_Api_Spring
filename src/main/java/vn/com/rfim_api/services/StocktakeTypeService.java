package vn.com.rfim_api.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.com.rfim_api.persistences.entities.StocktakeType;
import vn.com.rfim_api.persistences.repositories.StocktakeTypeRepository;
import vn.com.rfim_api.services.dtos.StocktakeTypeDTO;
import vn.com.rfim_api.services.jsonobjects.ResponseMesasge;

import java.util.List;

@Service
public class StocktakeTypeService {

    @Autowired
    private StocktakeTypeRepository context;
    @Autowired
    private ModelMapper mapper;

    //Get all stocktake type
    public ResponseEntity getAllStocktakeType() {
        List<StocktakeType> result = context.getAll();
        if (result.size() > 0) {
            List<StocktakeTypeDTO> stocktakeTypeDTOS =
                    mapper.map(result, new TypeToken<List<StocktakeTypeDTO>>(){}.getType());
            return new ResponseEntity(stocktakeTypeDTOS, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}

package vn.com.rfim_api.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.repositories.ShelfRepository;
import vn.com.rfim_api.services.dtos.ShelfDTO;

import java.util.List;

@Service
@Transactional
public class ShelfService {

    @Autowired
    private ShelfRepository context;
    @Autowired
    private ModelMapper mapper;

    public ResponseEntity getAll() {
        List<ShelfDTO> shelves = mapper.map(context.getAll(), new TypeToken<List<ShelfDTO>>(){}.getType());
        if (shelves.size() > 0) {
            return new ResponseEntity(shelves, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    //Get shelf by floor id
    public ResponseEntity getShelfByFloorId(String floorId) {
        ShelfDTO shelf = mapper.map(context.getByFloorId(floorId), ShelfDTO.class);
        if (shelf != null) {
            return new ResponseEntity(shelf, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}

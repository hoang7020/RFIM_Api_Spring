package vn.com.rfim_api.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.repositories.FloorRepository;
import vn.com.rfim_api.services.dtos.FloorDTO;

import java.util.List;

@Service
@Transactional
public class FloorService {

    @Autowired
    private FloorRepository context;
    @Autowired
    private ModelMapper mapper;

    public List<FloorDTO> getAll() {
        List<FloorDTO> floors = mapper.map(context.getAll(), new TypeToken<List<FloorDTO>>(){}.getType());
        return floors;
    }

    //Get floor by shelf id
    public ResponseEntity getFloorByShelfId(String shelfId) {
        List<FloorDTO> floors = mapper.map(context.getByShelfId(shelfId), new TypeToken<List<FloorDTO>>(){}.getType());
        if (floors.size() > 0) {
            return new ResponseEntity(floors, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    //Get floor by cell id
    public ResponseEntity getFloorByCellId(String cellId) {
        FloorDTO floor = mapper.map(context.getByCellId(cellId), FloorDTO.class);
        if (floor != null) {
            return new ResponseEntity(floor, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}

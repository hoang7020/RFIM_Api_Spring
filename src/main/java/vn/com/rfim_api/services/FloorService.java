package vn.com.rfim_api.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<FloorDTO> getByShelfId(String shelfId) {
        List<FloorDTO> floors = mapper.map(context.getByShelfId(shelfId), new TypeToken<List<FloorDTO>>(){}.getType());
        return floors;
    }

}

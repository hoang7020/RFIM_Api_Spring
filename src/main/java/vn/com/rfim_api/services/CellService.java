package vn.com.rfim_api.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.repositories.CellRepository;
import vn.com.rfim_api.services.dtos.CellDTO;
import vn.com.rfim_api.services.jsonobjects.CellData;
import vn.com.rfim_api.services.jsonobjects.ResultResponse;

import java.util.List;

@Service
@Transactional
public class CellService {

    @Autowired
    private CellRepository context;
    @Autowired
    private ModelMapper mapper;

    //Get Cell by using floor id
    public ResponseEntity getByFloorId(String floorId) {
        ResultResponse response = new ResultResponse();
        List<CellDTO> cells = mapper.map(context.getByFloorId(floorId), new TypeToken<List<CellDTO>>(){}.getType());
        if (cells.size() > 0) {
            response.setMessage("OK");
            response.setData(new CellData(cells));
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            response.setMessage("No Cell Found!");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }

    //Map cell with rfid tag
    public ResponseEntity registerCell(String cellId, String cellRfid) {
        ResultResponse response = new ResultResponse();
        boolean result = context.registerCell(cellId, cellRfid);
        if (result) {
            response.setMessage("Register Cell Success!");
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            response.setMessage("Register Cell Fail!");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }

}

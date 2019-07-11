package vn.com.rfim_api.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.constants.Constant;
import vn.com.rfim_api.persistences.entities.Cell;
import vn.com.rfim_api.persistences.repositories.CellRepository;
import vn.com.rfim_api.services.dtos.CellDTO;
import vn.com.rfim_api.services.jsonobjects.ResponseMesasge;

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
        List<CellDTO> cells = mapper.map(context.getByFloorId(floorId), new TypeToken<List<CellDTO>>() {
        }.getType());
        if (cells.size() > 0) {
            return new ResponseEntity(cells, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //Map cell with rfid tag
    public ResponseEntity registerCell(String cellId, String cellRfid) {
        ResponseMesasge response = new ResponseMesasge();
        boolean result = context.registerCell(cellId, cellRfid);
        if (result) {
            response.setMessage(Constant.REGISTER_CELL_SUCCESSFULLY);
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            response.setMessage(Constant.REGISTER_CELL_FAIL);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }


    //Get cell by cell rfid
    public ResponseEntity getCellByCellRfid(String rfid) {
        ResponseMesasge response = new ResponseMesasge();
        Cell result = context.getByCellRfid(rfid);
        if (result != null) {
            CellDTO cell = mapper.map(result, CellDTO.class);
            return new ResponseEntity(cell, HttpStatus.OK);
        } else {
            response.setMessage(Constant.CELL_NOT_EXIT);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }
}

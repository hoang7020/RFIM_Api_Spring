package vn.com.rfim_api.persistences.repositories;

import vn.com.rfim_api.persistences.entities.Cell;

import java.util.List;

public interface CellRepository {

    public List<Cell> getByFloorId(String floorId);

    public boolean registerCell(String cellId, String cellRfid);

    public Cell getByCellRfid(String rfid);
    
}

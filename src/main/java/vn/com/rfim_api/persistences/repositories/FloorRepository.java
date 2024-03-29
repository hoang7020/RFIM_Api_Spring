package vn.com.rfim_api.persistences.repositories;

import vn.com.rfim_api.persistences.entities.Floor;

import java.util.List;

public interface FloorRepository {

    public List<Floor> getByShelfId(String shelfId);

    public Floor getByCellId(String cellId);
}

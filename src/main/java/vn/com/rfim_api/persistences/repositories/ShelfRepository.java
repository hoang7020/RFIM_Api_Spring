package vn.com.rfim_api.persistences.repositories;


import vn.com.rfim_api.persistences.entities.Shelf;
import vn.com.rfim_api.services.jsonobjects.CellInfo;

import java.sql.Date;
import java.util.List;

public interface ShelfRepository {

    public List<Shelf> getAll();

    public Shelf getByFloorId(String floorId);

//    public Shelf getByCellId(String cellId);

    public List<Shelf> getShelvesContainProductId(String productId);

    public Date getMinDateOfAProductId(String shelfId, String productId);

    public List<CellInfo> getCellInfoOfShelf(String shelfId, String productId);

    public Shelf getByShelfId(String shelfId);
}

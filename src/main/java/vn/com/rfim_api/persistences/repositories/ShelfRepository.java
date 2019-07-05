package vn.com.rfim_api.persistences.repositories;


import vn.com.rfim_api.persistences.entities.Shelf;

import java.util.List;

public interface ShelfRepository {

    public List<Shelf> getAll();

    public Shelf getByFloorId(String floorId);

    public Shelf getByPackageRfid(String packageRfid);
}

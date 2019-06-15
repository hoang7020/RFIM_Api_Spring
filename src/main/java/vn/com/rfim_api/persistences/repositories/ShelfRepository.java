package vn.com.rfim_api.persistences.repositories;


import vn.com.rfim_api.persistences.entities.Shelf;

import java.util.List;

public interface ShelfRepository {

    public List<Shelf> getAll();
}

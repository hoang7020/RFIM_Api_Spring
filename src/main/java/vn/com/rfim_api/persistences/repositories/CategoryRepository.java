package vn.com.rfim_api.persistences.repositories;

import vn.com.rfim_api.persistences.entities.Category;

import java.util.List;

public interface CategoryRepository {

    public List<Category> getAll();

}

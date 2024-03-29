package vn.com.rfim_api.persistences.repositories;

import vn.com.rfim_api.persistences.entities.Product;

import java.util.List;

public interface ProductRepository {

    public List<Product> getAll();

    public List<Product> getByCategoryId(String categoryId);

    public Product getByBoxRfid(String boxRfid);

    public Product getByPackageRfid(String packageRfid);

}

package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.entities.Box;
import vn.com.rfim_api.persistences.entities.Package;
import vn.com.rfim_api.persistences.entities.Product;
import vn.com.rfim_api.persistences.repositories.ProductRepository;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private SessionFactory sessionFactory;

    //Get all products
    @Override
    public List<Product> getAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Product where Status = 'true'", Product.class);
        List<Product> products = query.getResultList();
        return products;
    }

    //Get products by category id
    @Override
    public List<Product> getByCategoryId(String categoryId) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Product P where P.category.categoryId = :categoryId", Product.class);
        query.setParameter("categoryId", categoryId);
        List<Product> products = query.getResultList();
        return products;
    }

    //Get product by box rfid
    @Override
    public Product getByBoxRfid(String boxRfid) {
        Session session = this.sessionFactory.getCurrentSession();
        Product product = null;
        Box box = session.get(Box.class, boxRfid);
        if (box != null) {
            product = box.getProduct();
        }
        return product;
    }

    //Get product by package rfid
    @Override
    public Product getByPackageRfid(String packageRfid) {
        Session session = this.sessionFactory.getCurrentSession();
        Product product = null;
        Package pac = session.get(Package.class, packageRfid);
        if (pac != null) {
            product = pac.getProduct();
        }
        return product;
    }


}

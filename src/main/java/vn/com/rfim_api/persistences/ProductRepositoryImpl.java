package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.entities.Box;
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
        List<Product> products = session.createQuery("from Product", Product.class).getResultList();
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

    //Get product by box id
    @Override
    public Product getByBoxId(String boxId) {
        Session session = this.sessionFactory.getCurrentSession();
        Product product = null;
        Box box = session.get(Box.class, boxId);
        if (box != null) {
            product = box.getProduct();
        }
        return product;
    }

}

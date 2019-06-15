package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.entities.Package;
import vn.com.rfim_api.persistences.entities.Product;
import vn.com.rfim_api.persistences.repositories.PackageRepository;

@Repository
@Transactional(rollbackFor = Exception.class)
public class PackageRepositoryImpl implements PackageRepository {

    @Autowired
    private SessionFactory sessionFactory;

    //Create new packaged by using rfid id and masp with product id
    @Override
    public boolean registerPackage(String packagedid, String productId) {
        Session session = this.sessionFactory.getCurrentSession();
//        NativeQuery query = session.createSQLQuery("insert into Package(packagedId, quantity, productId) values(:packagedId, :quantity, :productId)");
//        query.setParameter("packagedId", packagedid);
//        query.setParameter("quantity", 0);
//        query.setParameter("productId", productId);
//        int result = query.executeUpdate();
//        if (result > 0) {
//            return true;
//        }
//        return false;

        Package pac = new Package();
        pac.setPackageId(packagedid);
        Product product = session.load(Product.class, productId);
        pac.setProduct(product);
        session.save(pac);
        return true;
    }
}

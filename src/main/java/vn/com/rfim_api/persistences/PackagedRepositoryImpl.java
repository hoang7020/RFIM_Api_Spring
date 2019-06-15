package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.entities.Cell;
import vn.com.rfim_api.persistences.entities.Packaged;
import vn.com.rfim_api.persistences.entities.Product;
import vn.com.rfim_api.persistences.repositories.PackagedRepository;

@Repository
@Transactional(rollbackFor = Exception.class)
public class PackagedRepositoryImpl implements PackagedRepository {

    @Autowired
    private SessionFactory sessionFactory;

    //Create new packaged by using rfid id and masp with product id
    @Override
    public boolean registerPackage(String packagedid, String productId) {
        Session session = this.sessionFactory.getCurrentSession();
//        NativeQuery query = session.createSQLQuery("insert into Packaged(packagedId, quantity, productId) values(:packagedId, :quantity, :productId)");
//        query.setParameter("packagedId", packagedid);
//        query.setParameter("quantity", 0);
//        query.setParameter("productId", productId);
//        int result = query.executeUpdate();
//        if (result > 0) {
//            return true;
//        }
//        return false;

        Packaged dto = new Packaged();
        dto.setPackagedId(packagedid);
        Product p = session.load(Product.class, productId);
        dto.setProduct(p);
        Cell c = session.load(Cell.class, "A-1-1");
        dto.setCell(c);
        session.save(dto);
        return true;
    }
}

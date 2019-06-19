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
import vn.com.rfim_api.persistences.repositories.PackageRepository;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class PackageRepositoryImpl implements PackageRepository {

    @Autowired
    private SessionFactory sessionFactory;

    //Create new packaged by using rfid id and masp with product id
    @Override
    public boolean addPackage(String packagedRfid, String productId) {
        Session session = this.sessionFactory.getCurrentSession();
        Package pac = new Package();
        pac.setPackageId(packagedRfid);
        Product product = session.load(Product.class, productId);
        pac.setProduct(product);
        session.save(pac);
        return true;
    }

    //Map package with cell id
    @Override
    public boolean updatePackageCellId(String packageId, String cellId) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Package set cell.cellId = :cellId where packageId = :packageId");
        query.setParameter("cellId", cellId);
        query.setParameter("packageId", packageId);
        int result = query.executeUpdate();
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    //Check package is exit
    @Override
    public boolean isExit(String packageId) {
        Session session = this.sessionFactory.getCurrentSession();
        Package pac = session.get(Package.class, packageId);
        if (pac != null) {
            return true;
        }
        return false;
    }

    //Delete package when package is empty
    @Override
    public boolean deletePackage(String packageId) {
        Session session = this.sessionFactory.getCurrentSession();
        Package pac = session.load(Package.class, packageId);
        session.delete(pac);
        if (!isExit(packageId)) {
            return true;
        }
        return false;
    }

    //Check package is empty
    @Override
    public boolean isEmpty(String packageId) {
        Session session = this.sessionFactory.getCurrentSession();
        Package pac = session.load(Package.class, packageId);
        List<Box> boxes = pac.getBoxes();
        if (boxes.isEmpty()) {
            return true;
        }
        return false;
    }
}

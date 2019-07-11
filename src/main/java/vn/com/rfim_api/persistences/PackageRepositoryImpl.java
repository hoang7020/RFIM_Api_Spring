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

import java.sql.Date;
import java.sql.Timestamp;
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
        pac.setPackageRfid(packagedRfid);
        Product product = session.load(Product.class, productId);
        pac.setProduct(product);
        session.save(pac);
        return true;
    }

    //Map package with cell id
    //Stock in package
    public boolean stockinPackage(String packageRfid, String cellId, Timestamp date) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Package set cell.cellId = :cellId, date = :date where packageRfid = :packageRfid");
        query.setParameter("cellId", cellId);
        query.setParameter("date", new Date(date.getTime()));
        query.setParameter("packageRfid", packageRfid);
        int result = query.executeUpdate();
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    //Map package with cell id
    //Transfer package
    @Override
    public boolean updatePackageCellId(String packageRfid, String cellId) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Package set cell.cellId = :cellId where packageRfid = :packageRfid");
        query.setParameter("cellId", cellId);
        query.setParameter("packageRfid", packageRfid);
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
        Package pac = session.get(Package.class, packageId);
        session.delete(pac);
        if (!isExit(packageId)) {
            return true;
        }
        return false;
    }

    //Check package is empty
    @Override
    public boolean isEmpty(String packageRfid) {
        Session session = this.sessionFactory.getCurrentSession();
        Package pac = session.get(Package.class, packageRfid);
        int result = pac.getBoxes().size();
        if (result == 0) {
            return true;
        }
        return false;
    }

    //Check package was stocked in or not
    @Override
    public boolean isStockIn(String packageRfid) {
        Session session = this.sessionFactory.getCurrentSession();
        Package pac = session.get(Package.class, packageRfid);
        if (pac.getCell() != null) {
            return true;
        }
        return false;
    }

    //Get package by pacakge rfid
    @Override
    public Package getByPackageRfid(String packageRfid) {
        Session session = this.sessionFactory.getCurrentSession();
        Package pac = session.get(Package.class, packageRfid);
        if (pac != null) {
            return pac;
        }
        return null;
    }

    //Get earliest package in warehouse
    @Override
    public Package getEarliesPackageByProductId(String productId) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Package P where ProductId = :productId order by Date");
        query.setParameter("productId", productId);
        List<Package> pac = query.getResultList();
        if (pac.size() > 0) {
            return pac.get(0);
        }
        return null;
    }
}

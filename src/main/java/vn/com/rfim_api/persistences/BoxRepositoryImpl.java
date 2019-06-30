package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.com.rfim_api.persistences.entities.Box;
import vn.com.rfim_api.persistences.entities.Package;
import vn.com.rfim_api.persistences.entities.Product;
import vn.com.rfim_api.persistences.repositories.BoxRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class BoxRepositoryImpl implements BoxRepository {

    @Autowired
    private SessionFactory sessionFactory;

    //Add a list of box
    //Map all box with package id
    @Override
    public void addBatchBox(List<String> boxRfids, String packageId, String productId) {
        Session session = this.sessionFactory.getCurrentSession();
        for (String rfid : boxRfids) {
            Box newbox = new Box();
            newbox.setBoxRfid(rfid);
            newbox.setProduct(session.get(Product.class, productId));
            Package pac = session.load(Package.class, packageId);
            newbox.setaPackage(pac);
            session.save(newbox);
            session.flush();
        }
    }

    //Delete box when stock out
    @Override
    public List<String> deleteBatchBox(List<String> boxRfids) {
        Session session = this.sessionFactory.getCurrentSession();
        List<String> listPackages = new ArrayList<>();
        for (String rfid: boxRfids) {
            Box delBox = session.get(Box.class, rfid);
            if (!listPackages.contains(delBox.getaPackage().getPackageRfid())) {
                listPackages.add(delBox.getaPackage().getPackageRfid());
            }
            session.delete(delBox);
            session.flush();
        }
        return listPackages;
    }

    //Check box is exit
    @Override
    public boolean isExit(String boxId) {
        Session session = this.sessionFactory.getCurrentSession();
        Box box = session.get(Box.class, boxId);
        if (box != null) {
            return true;
        }
        return false;
    }

    //Update package rfid of box (transfer box between package)
    @Override
    public List<String> updateBatchBoxPackageRfid(String packageRfid, List<String> boxRfids) {
        Session session = this.sessionFactory.getCurrentSession();
        List<String> listPackages = new ArrayList<>();
        for (String boxRfid: boxRfids) {
            Box updateBox = session.get(Box.class, boxRfid);
            if (!listPackages.contains(updateBox.getaPackage().getPackageRfid())) {
                listPackages.add(updateBox.getaPackage().getPackageRfid());
            }
            Package pac = new Package();
            pac.setPackageRfid(packageRfid);
            updateBox.setaPackage(pac);
            session.update(updateBox);
            session.flush();
        }
        return listPackages;
    }

    //Get all box rfids by product id
    @Override
    public List<String> getByProductId(String productId) {
        Session session = this.sessionFactory.getCurrentSession();
        NativeQuery query = session.createNativeQuery("select BoxRFID from box where ProductId = :productId");
        query.setParameter("productId", productId);
        List<String> boxRfids = query.getResultList();
        return boxRfids;
    }
}

package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.com.rfim_api.persistences.entities.Box;
import vn.com.rfim_api.persistences.entities.Package;
import vn.com.rfim_api.persistences.repositories.BoxRepository;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class BoxRepositoryImpl implements BoxRepository {

    @Autowired
    private SessionFactory sessionFactory;

    //Add a list of box
    //Map all box with package id
    @Override
    public void addBatchBox(List<String> boxRfids, String packageId) {
        Session session = this.sessionFactory.getCurrentSession();
        for (String rfid : boxRfids) {
            Box newbox = new Box();
            newbox.setBoxId(rfid);
            Package pac = session.load(Package.class, packageId);
            newbox.setaPackage(pac);
            session.save(newbox);
        }
    }

    //Delete box when stock out
    @Override
    public boolean deleteBox(String boxId) {
        Session session = this.sessionFactory.getCurrentSession();
        Box box = session.load(Box.class, boxId);
        session.delete(box);
        if (!isExit(boxId)) {
            return true;
        }
        return false;
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

    //Update cell id of box (transfer box between package)
    @Override
    public boolean updateBoxPackageId(String boxId, String packageId) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Box set package.packageId = :packageId where boxId = :boxId");
        query.setParameter("packageId", packageId);
        query.setParameter("boxId", boxId);
        int result = query.executeUpdate();
        if (result > 0) {
            return true;
        }
        return false;
    }
}

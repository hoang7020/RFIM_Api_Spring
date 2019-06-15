package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.entities.Box;
import vn.com.rfim_api.persistences.entities.Package;
import vn.com.rfim_api.persistences.repositories.BoxRepository;

@Repository
@Transactional(rollbackFor = Exception.class)
public class BoxRepositoryImpl implements BoxRepository {

    @Autowired
    private SessionFactory sessionFactory;

    //Create new box by using rfid id and map with package
    @Override
    public boolean addBox(String boxId, String packageId) {
        Session session = this.sessionFactory.getCurrentSession();
        Box newBox = new Box();
        newBox.setBoxId(boxId);
        Package pac = session.load(Package.class, packageId);
        newBox.setaPackage(pac);
        session.save(newBox);
        return true;
    }
}

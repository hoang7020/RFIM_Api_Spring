package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.entities.Floor;
import vn.com.rfim_api.persistences.repositories.FloorRepository;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class FloorRepositoryImpl implements FloorRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Floor> getAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Floor> floors = session.createQuery("from Floor", Floor.class).getResultList();
        return floors;
    }

    @Override
    public List<Floor> getByShelfId(String shelfId) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Floor F where F.shelf.shelfId = :id", Floor.class);
        query.setParameter("id", shelfId);
        List<Floor> floors = query.getResultList();
        return floors;
    }
}

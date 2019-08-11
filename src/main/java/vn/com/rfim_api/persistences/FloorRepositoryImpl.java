package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.entities.Cell;
import vn.com.rfim_api.persistences.entities.Floor;
import vn.com.rfim_api.persistences.repositories.FloorRepository;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class FloorRepositoryImpl implements FloorRepository {

    @Autowired
    private SessionFactory sessionFactory;

    //get floor by shelf id
    @Override
    public List<Floor> getByShelfId(String shelfId) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Floor F where F.shelf.shelfId = :id and Status = '1'", Floor.class);
        query.setParameter("id", shelfId);
        List<Floor> floors = query.getResultList();
        return floors;
    }

    //get floor by cell id
    @Override
    public Floor getByCellId(String cellId) {
        Session session = this.sessionFactory.getCurrentSession();
        Cell cell = session.get(Cell.class, cellId);
        Floor floor = cell.getFloor();
        return floor;
    }
}

package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.entities.Cell;
import vn.com.rfim_api.persistences.repositories.CellRepository;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class CellRepositoryImpl implements CellRepository {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<Cell> getByFloorId(String floorId) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Cell C where C.floor.floorId = :id", Cell.class);
        query.setParameter("id", floorId);
        List<Cell> cells = query.getResultList();
        return cells;
    }

    @Override
    public boolean registerCell(String cellId, String cellRfid) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Cell set cellRfid = :cellRfid " +
                "where cellID = :cellId");
        query.setParameter("cellRfid", cellRfid);
        query.setParameter("cellId", cellId);
        int result = query.executeUpdate();
        if (result > 0) {
            return true;
        }
        return false;
    }
}

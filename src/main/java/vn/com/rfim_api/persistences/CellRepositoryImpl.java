package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.entities.Cell;
import vn.com.rfim_api.persistences.entities.Shelf;
import vn.com.rfim_api.persistences.repositories.CellRepository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class CellRepositoryImpl implements CellRepository {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ModelMapper mapper;

    //Get cell by floor id
    @Override
    public List<Cell> getByFloorId(String floorId) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Cell C where C.floor.floorId = :id and Status ='1'", Cell.class);
        query.setParameter("id", floorId);
        List<Cell> cells = query.getResultList();
        return cells;
    }

    //Register cell with cell rfid
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

    @Override
    public Cell getByCellRfid(String rfid) {
        Session session = this.sessionFactory.getCurrentSession();
        try {
            Query query = session.createQuery("from Cell where cellRfid = :rfid and Status = '1'");
            query.setParameter("rfid", rfid);
            Cell cell = (Cell) query.getSingleResult();
            if (cell != null) {
                return cell;
            }
        } catch (NoResultException ex) {
            return null;
        }
        return null;
    }

}

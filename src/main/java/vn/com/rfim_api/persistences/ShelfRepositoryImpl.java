package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.entities.Cell;
import vn.com.rfim_api.persistences.entities.Floor;
import vn.com.rfim_api.persistences.entities.Shelf;
import vn.com.rfim_api.persistences.repositories.ShelfRepository;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class ShelfRepositoryImpl implements ShelfRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Shelf> getAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Shelf> shelves = session.createQuery("from Shelf", Shelf.class).getResultList();
        return shelves;
    }

    //Get shelf by floor id
    @Override
    public Shelf getByFloorId(String floorId) {
        Session session = this.sessionFactory.getCurrentSession();
        Floor floor = session.get(Floor.class, floorId);
        Shelf shelf = floor.getShelf();
        return shelf;
    }

    //Get shelf by package id
    @Override
    public Shelf getByPackageRfid(String packageRfid) {
        Session session = this.sessionFactory.getCurrentSession();
        Cell cell = session.load(Cell.class, packageRfid);
        Floor floor = cell.getFloor();
        Shelf shelf = floor.getShelf();
        return shelf;
    }

}

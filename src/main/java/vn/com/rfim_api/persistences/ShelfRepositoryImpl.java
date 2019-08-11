package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.entities.Cell;
import vn.com.rfim_api.persistences.entities.Floor;
import vn.com.rfim_api.persistences.entities.Shelf;
import vn.com.rfim_api.persistences.repositories.ShelfRepository;
import vn.com.rfim_api.services.jsonobjects.CellInfo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class ShelfRepositoryImpl implements ShelfRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Shelf> getAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Shelf where Status = '1'", Shelf.class);
        List<Shelf> shelves = query.getResultList();
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
//    @Override
//    public Shelf getByCellId(String cellId) {
//        Session session = this.sessionFactory.getCurrentSession();
//        Cell cell = session.load(Cell.class, cellId);
//        if (cell != null) {
//            Floor floor = cell.getFloor();
//            Shelf shelf = floor.getShelf();
//            return shelf;
//        } else {
//            return null;
//        }
//    }

    //Get shelves contain product id
    @Override
    public List<Shelf> getShelvesContainProductId(String productId) {
        Session session = this.sessionFactory.getCurrentSession();
        NativeQuery query = session.createNativeQuery(
                "select Shelf.ShelfId, Shelf.CoorX, Shelf.CoorY from" +
                        " (((shelf inner join floor on shelf.ShelfId = Floor.ShelfId)" +
                        " inner join cell on floor.FloorId = cell.FloorId)" +
                        " inner join package on cell.CellId = package.CellId)" +
                        " inner join box on package.packageRfid = box.packageRfid" +
                        " where box.ProductId = :productId and Box.Status = 'true'" +
                        " group by Shelf.ShelfId, Shelf.CoorX, Shelf.CoorY"
        );
        query.setParameter("productId", productId);
        List<Object[]> objs = query.getResultList();
        List<Shelf> shelves = new ArrayList<>();
        for (Object[] obj: objs) {
//            Shelf shelf = new Shelf((String) obj[0], (Integer)obj[1], (Integer) obj[2]);
//            shelves.add(shelf);
            Shelf shelf = session.get(Shelf.class, (String) obj[0]);
            shelves.add(shelf);
        }
        return shelves;
    }

    @Override
    public Date getMinDateOfAProductId(String shelfId, String productId) {
        Session session = this.sessionFactory.getCurrentSession();
        NativeQuery query = session.createNativeQuery(
            "select min(Box.Date) as MinDate from" +
                    " (((Shelf inner join Floor on Shelf.ShelfId = Floor.ShelfId)" +
                    " inner join Cell on Floor.FloorId = Cell.FloorId)" +
                    " inner join Package on Cell.CellId = Package.CellId)" +
                    " inner join Box on Package.PackageRfid = Box.PackageRfid" +
                    " where Shelf.ShelfId = :shelfId and Box.ProductId = :productId and Box.Status = 'true'"
        );
        query.setParameter("shelfId", shelfId);
        query.setParameter("productId", productId);
        Date date = (Date) query.getSingleResult();
        return date;
    }

    //Get cell info (cell id and quantiy of box contain in cell)
    @Override
    public List<CellInfo> getCellInfoOfShelf(String shelfId, String productId) {
        Session session = this.sessionFactory.getCurrentSession();
        NativeQuery query = session.createNativeQuery(
            "select Cell.CellId, Box.Date, Count(Box.BoxRFID) as Quantity" +
                    " from (((Shelf inner join Floor on Shelf.ShelfId = Floor.ShelfId)" +
                    " inner join Cell on Floor.FloorId = Cell.FloorId)" +
                    " inner join Package on Cell.CellId = Package.CellId)" +
                    " inner join Box on Package.packageRfid = Box.packageRfid" +
                    " where Box.ProductId = :productId and Shelf.ShelfId = :shelfId and Box.Status = 'true'" +
                    " group by Cell.CellId, Box.Date" +
                    " order by Box.Date"
        );
        query.setParameter("productId", productId);
        query.setParameter("shelfId", shelfId);
        List<Object[]> objs = query.getResultList();
        List<CellInfo> cellInfos = new ArrayList<>();
        for (Object[] obj: objs) {
            CellInfo info = new CellInfo((String) obj[0], (Date) obj[1], (Integer) obj[2]);
            cellInfos.add(info);
        }
        return cellInfos;
    }

    //Get shelf by shelf id
    @Override
    public Shelf getByShelfId(String shelfId) {
        Session session = this.sessionFactory.getCurrentSession();
        Shelf shelf = session.get(Shelf.class, shelfId);
        return shelf;
    }

}

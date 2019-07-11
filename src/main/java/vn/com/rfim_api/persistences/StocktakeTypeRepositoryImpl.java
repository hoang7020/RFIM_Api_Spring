package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.entities.StocktakeType;
import vn.com.rfim_api.persistences.repositories.StocktakeTypeRepository;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class StocktakeTypeRepositoryImpl implements StocktakeTypeRepository {

    @Autowired
    private SessionFactory sessionFactory;

    //Get all stocktake type
//    @Override
//    public List<StocktakeType> getAll() {
//        Session session = this.sessionFactory.getCurrentSession();
//        Query query = session.createQuery("from StocktakeType");
//        List<StocktakeType> stocktakeTypes = query.getResultList();
//        return stocktakeTypes;
//    }
}

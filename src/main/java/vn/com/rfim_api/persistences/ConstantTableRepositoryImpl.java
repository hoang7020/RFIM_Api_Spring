package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.entities.ConstantTable;
import vn.com.rfim_api.persistences.repositories.ConstantTableRespository;


@Repository
@Transactional(rollbackFor = Exception.class)
public class ConstantTableRepositoryImpl implements ConstantTableRespository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ConstantTable getConstantTable() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ConstantTable", ConstantTable.class);
        ConstantTable constantTable = (ConstantTable) query.getSingleResult();
        return constantTable;
    }
}

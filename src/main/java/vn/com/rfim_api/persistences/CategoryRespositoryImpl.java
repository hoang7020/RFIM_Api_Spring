package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.entities.Category;
import vn.com.rfim_api.persistences.repositories.CategoryRepository;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class CategoryRespositoryImpl implements CategoryRepository {

    @Autowired
    private SessionFactory sessionFactory;

    //Get all category
    @Override
    public List<Category> getAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Category> categories = session.createQuery("from Category", Category.class).getResultList();
        return categories;
    }
}

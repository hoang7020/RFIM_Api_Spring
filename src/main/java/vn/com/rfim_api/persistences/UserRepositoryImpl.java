package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.entities.User;
import vn.com.rfim_api.persistences.repositories.UserRepository;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getByUsername(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where username = :username", User.class);
        query.setParameter("username", username);
        User user = (User) query.getSingleResult();
        return user;
    }

}

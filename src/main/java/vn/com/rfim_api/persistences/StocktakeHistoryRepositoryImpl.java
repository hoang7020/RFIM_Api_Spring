package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.entities.Product;
import vn.com.rfim_api.persistences.entities.StocktakeHistory;
import vn.com.rfim_api.persistences.entities.StocktakeType;
import vn.com.rfim_api.persistences.entities.User;
import vn.com.rfim_api.persistences.repositories.StocktakeHistoryRepository;

import java.sql.Timestamp;
import java.util.Date;

@Repository
@Transactional(rollbackFor = Exception.class)
public class StocktakeHistoryRepositoryImpl implements StocktakeHistoryRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean addStocktakeHistory(int userId, String productId,
                                       int quantity, Timestamp date, String description) {
        Session session = this.sessionFactory.getCurrentSession();
//        StocktakeHistory stocktakeHistory = new StocktakeHistory();
//        User user = session.get(User.class, userId);
//        stocktakeHistory.setUser(user);
//        Product product = session.get(Product.class, productId);
//        stocktakeHistory.setProduct(product);
//        stocktakeHistory.setDate(date);
//        StocktakeType stocktakeType = session.get(StocktakeType.class, stocktakeTypeId);
//        stocktakeHistory.setStocktakeType(stocktakeType);
//        session.save(stocktakeHistory);
        NativeQuery query = session.createNativeQuery("insert into StocktakeHistory(UserId, ProductId, Quantity, Date, Description)" +
                "values(:userId, :productId, :quantity, :date, :description)");
//        query.setParameter("stocktakeTypeId", stocktakeTypeId);
        query.setParameter("userId", userId);
        query.setParameter("productId", productId);
        query.setParameter("quantity", quantity);
        query.setParameter("date", date);
        query.setParameter("description", description);
        int result = query.executeUpdate();
        if (result > 0) {
            return true;
        }
        return false;
    }
}

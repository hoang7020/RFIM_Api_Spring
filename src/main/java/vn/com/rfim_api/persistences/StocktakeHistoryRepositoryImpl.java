package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.repositories.StocktakeHistoryRepository;

import java.sql.Timestamp;

@Repository
@Transactional(rollbackFor = Exception.class)
public class StocktakeHistoryRepositoryImpl implements StocktakeHistoryRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean addStocktakeHistory(int userId, String productId,
                                       int quantity, Timestamp date, String lostBox, String foundBox) {
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
        NativeQuery query = session.createNativeQuery("insert into StocktakeHistory(UserId, ProductId, Quantity, Date, Status, LostBox, FoundBox)" +
                "values(:userId, :productId, :quantity, :date, :status, :lostBox, :foundBox)");
//        query.setParameter("stocktakeTypeId", stocktakeTypeId);
        query.setParameter("userId", userId);
        query.setParameter("productId", productId);
        query.setParameter("quantity", quantity);
        query.setParameter("date", date);
        query.setParameter("status", true);
        query.setParameter("lostBox", lostBox);
        System.out.println(lostBox);
        query.setParameter("foundBox", foundBox);
        System.out.println(foundBox);
        int result = query.executeUpdate();
        if (result > 0) {
            return true;
        }
        return false;
    }
}

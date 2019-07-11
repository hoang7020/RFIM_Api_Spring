package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.entities.InvoiceProduct;
import vn.com.rfim_api.persistences.repositories.InvoiceProductRepository;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class InvoiceProductRepositoryImpl implements InvoiceProductRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public InvoiceProduct getByInvoiceIdAndProductId(String invoiceId, String productId) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from InvoiceProduct where invoiceId = :invoiceId and productId = :productId");
        query.setParameter("invoiceId", invoiceId);
        query.setParameter("productId", productId);
        InvoiceProduct invoiceProduct = (InvoiceProduct) query.getSingleResult();
        if (invoiceProduct != null) {
            return invoiceProduct;
        }
        return null;
    }

    //Update processQuantity of InvoiceProduct
    @Override
    public void updateProcessQuantity(String invoiceId, String productId, int processQuantity) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("update InvoiceProduct set ProcessQuantity = :processQuantity where " +
                "invoiceId = :invoiceId and productId = :productId");
        query.setParameter("processQuantity", processQuantity);
        query.setParameter("invoiceId", invoiceId);
        query.setParameter("productId", productId);
        query.executeUpdate();
        session.clear();
    }

    @Override
    public List<InvoiceProduct> getByInvoiceId(String invoiceId) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from InvoiceProduct where invoiceId = :invoiceId");
        query.setParameter("invoiceId", invoiceId);
        List<InvoiceProduct> invoiceProducts = query.getResultList();
        return invoiceProducts;
    }


}

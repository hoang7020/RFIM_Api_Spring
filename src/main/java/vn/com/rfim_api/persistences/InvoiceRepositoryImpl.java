package vn.com.rfim_api.persistences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.persistences.entities.Invoice;
import vn.com.rfim_api.persistences.entities.InvoiceStatus;
import vn.com.rfim_api.persistences.repositories.InvoiceRepository;
import vn.com.rfim_api.services.jsonobjects.InvoiceInfoItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class InvoiceRepositoryImpl implements InvoiceRepository {

    @Autowired
    private SessionFactory sessionFactory;

    //Get invoice info by type and status
    @Override
    public List<InvoiceInfoItem> getInvoiceInfo(int type) {
        Session session = this.sessionFactory.getCurrentSession();
        NativeQuery query = session.createNativeQuery(
                "select Invoice.InvoiceId, Product.ProductId, Product.ProductName, Quantity, ProcessQuantity, Invoice.Date, Invoice.StatusId " +
                        "from ((Invoice_Product " +
                        "inner join Invoice on invoice.InvoiceId = Invoice_Product.InvoiceId) " +
                        "inner join Product on product.ProductId = Invoice_Product.ProductId) " +
                        "where InvoiceTypeId = :type"
        );
        query.setParameter("type", type);
        List<Object[]> objs = query.getResultList();
        List<InvoiceInfoItem> invoices = new ArrayList<>();
        for (Object[] obj: objs) {
            InvoiceInfoItem invoice = new InvoiceInfoItem((String) obj[0], (String) obj[1],
                    (String) obj[2],(Integer) obj[3], (Integer) obj[4], (Date) obj[5], (Integer) obj[6]);
            invoices.add(invoice);
        }
        return invoices;
    }

    //Update invoice status when register package , stock out
    @Override
    public void updateInvoiceStatus(String invoiceId, int status) {
        Session session = this.sessionFactory.getCurrentSession();
        Invoice invoice = session.get(Invoice.class, invoiceId);
        InvoiceStatus invoiceStatus = new InvoiceStatus();
        invoiceStatus.setStatusId(status);
        invoice.setInvoiceStatus(invoiceStatus);
        session.saveOrUpdate(invoice);
        session.flush();
    }

}

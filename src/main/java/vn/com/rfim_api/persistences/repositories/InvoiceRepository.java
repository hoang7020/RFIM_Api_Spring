package vn.com.rfim_api.persistences.repositories;

import vn.com.rfim_api.services.jsonobjects.InvoiceInfoItem;

import java.util.List;

public interface InvoiceRepository {

    public List<InvoiceInfoItem> getInvoiceInfo(int type, boolean status);

}

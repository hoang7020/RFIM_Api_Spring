package vn.com.rfim_api.persistences.repositories;

import vn.com.rfim_api.persistences.entities.Package;

import java.sql.Timestamp;

public interface PackageRepository {

    public boolean addPackage(String packagedRfid, String productId);

    public boolean updatePackageCellId(String packageRfid, String cellId);

    public boolean isExit(String packageRfid);

    public boolean deletePackage(String packageRfid);

    public boolean isEmpty(String packageRfid);

    public boolean isStockIn(String packageRfid);

    public Package getByPackageRfid(String packageRfid);

    public boolean stockinPackage(String packageRfid, String cellId, Timestamp date);

    public Package getEarliesPackageByProductId(String productId);

//    public Package getByCellId(String cellId);

}

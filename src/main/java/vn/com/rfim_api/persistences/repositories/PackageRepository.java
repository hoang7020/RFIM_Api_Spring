package vn.com.rfim_api.persistences.repositories;

public interface PackageRepository {

    public boolean addPackage(String packagedRfid, String productId);

    public boolean updatePackageCellId(String packageId, String cellId);

    public boolean isExit(String packageId);

    public boolean deletePackage(String packageId);

    public boolean isEmpty(String packageId);
}

package vn.com.rfim_api.persistences.repositories;

import java.util.List;

public interface BoxRepository {

    public void addBatchBox(List<String> boxRfids, String packageId, String productId);

    public List<String> deleteBatchBox(List<String> boxId);

    public boolean isExit(String boxId);

    public List<String> updateBatchBoxPackageRfid(String packageRfid, List<String> boxRfids);

    public List<String> getByProductId(String productId);

}
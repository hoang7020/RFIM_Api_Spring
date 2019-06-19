package vn.com.rfim_api.persistences.repositories;

import java.util.List;

public interface BoxRepository {

    public void addBatchBox(List<String> boxRfids, String packageId);

    public boolean deleteBox(String boxId);

    public boolean isExit(String boxId);

    public boolean updateBoxPackageId(String boxId, String packageId);

}
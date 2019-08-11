package vn.com.rfim_api.services.jsonobjects;

import java.util.Comparator;
import java.util.List;

public class AlgorithmResult implements Comparator<AlgorithmResult> {
    private String shelfId;
    private float weight;
    private List<CellInfo> cellInfos;

    public AlgorithmResult() {
    }

    public AlgorithmResult(String shelfId, float weight, List<CellInfo> cellInfos) {
        this.shelfId = shelfId;
        this.weight = weight;
        this.cellInfos = cellInfos;
    }

    public String getShelfId() {
        return shelfId;
    }

    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public List<CellInfo> getCellInfos() {
        return cellInfos;
    }

    public void setCellInfos(List<CellInfo> cellInfos) {
        this.cellInfos = cellInfos;
    }

    @Override
    public int compare(AlgorithmResult o1, AlgorithmResult o2) {
        return (int) (o2.getWeight() - o1.getWeight());
    }
}

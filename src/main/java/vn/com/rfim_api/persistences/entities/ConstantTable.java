package vn.com.rfim_api.persistences.entities;

import javax.persistence.*;

@Entity
@Table(name = "ConstantTable")
public class ConstantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int constantId;

    private float weightedDate;

    private float weightedDistance;

    private String sourceShelfId;

    private int columnDistance;

    private int rowDistance;

    public int getConstantId() {
        return constantId;
    }

    public void setConstantId(int constantId) {
        this.constantId = constantId;
    }

    public float getWeightedDate() {
        return weightedDate;
    }

    public void setWeightedDate(float weightedDate) {
        this.weightedDate = weightedDate;
    }

    public float getWeightedDistance() {
        return weightedDistance;
    }

    public void setWeightedDistance(float weightedDistance) {
        this.weightedDistance = weightedDistance;
    }

    public String getSourceShelfId() {
        return sourceShelfId;
    }

    public void setSourceShelfId(String sourceShelfId) {
        this.sourceShelfId = sourceShelfId;
    }

    public int getColumnDistance() {
        return columnDistance;
    }

    public void setColumnDistance(int columnDistance) {
        this.columnDistance = columnDistance;
    }

    public int getRowDistance() {
        return rowDistance;
    }

    public void setRowDistance(int rowDistance) {
        this.rowDistance = rowDistance;
    }
}

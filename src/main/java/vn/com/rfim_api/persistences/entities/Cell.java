package vn.com.rfim_api.persistences.entities;

import javax.persistence.*;

@Entity
@Table(name = "Cell")
public class Cell {

    @Id
    private String cellId;

    private int cellNo;

    private String cellRfid;

    private String description;

    @ManyToOne
    @JoinColumn(name = "floorId", nullable = false)
    private Floor floor;

    @OneToOne(mappedBy = "cell")
    private Packaged packaged;

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public int getCellNo() {
        return cellNo;
    }

    public void setCellNo(int cellNo) {
        this.cellNo = cellNo;
    }

    public String getCellRfid() {
        return cellRfid;
    }

    public void setCellRfid(String cellRfid) {
        this.cellRfid = cellRfid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Packaged getPackaged() {
        return packaged;
    }

    public void setPackaged(Packaged packaged) {
        this.packaged = packaged;
    }
}

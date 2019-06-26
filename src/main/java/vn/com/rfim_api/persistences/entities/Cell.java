package vn.com.rfim_api.persistences.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Cell")
public class Cell {

    @Id
    private String cellId;

    private String cellRfid;

    @ManyToOne
    @JoinColumn(name = "floorId", nullable = false)
    private Floor floor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cell")
    private List<Package> aPackages = new ArrayList<>();

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public String getCellRfid() {
        return cellRfid;
    }

    public void setCellRfid(String cellRfid) {
        this.cellRfid = cellRfid;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public List<Package> getaPackages() {
        return aPackages;
    }

    public void setaPackages(List<Package> aPackages) {
        this.aPackages = aPackages;
    }
}

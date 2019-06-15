package vn.com.rfim_api.persistences.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Floor")
public class Floor {

    @Id
    private String floorId;

    private int floorNo;

    private String description;

    @ManyToOne
    @JoinColumn(name = "shelfId", nullable = false)
    private Shelf shelf;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "floor")
    private List<Cell> listCells = new ArrayList<>();

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }
}

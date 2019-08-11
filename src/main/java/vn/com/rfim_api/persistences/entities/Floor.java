package vn.com.rfim_api.persistences.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Floor")
public class Floor {

    @Id
    private String floorId;

    private boolean status;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public List<Cell> getListCells() {
        return listCells;
    }

    public void setListCells(List<Cell> listCells) {
        this.listCells = listCells;
    }
}

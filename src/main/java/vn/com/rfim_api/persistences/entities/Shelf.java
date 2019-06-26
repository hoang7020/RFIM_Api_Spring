package vn.com.rfim_api.persistences.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Shelf")
public class Shelf {

    @Id
    private String shelfId;

    private String description;

    private int floorNumber;

    private int cellNumber;

    private int coorX;

    private int coorY;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shelf")
    private List<Floor> listFloor = new ArrayList<>();

    public String getShelfId() {
        return shelfId;
    }

    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public int getCoorX() {
        return coorX;
    }

    public void setCoorX(int coorX) {
        this.coorX = coorX;
    }

    public int getCoorY() {
        return coorY;
    }

    public void setCoorY(int coorY) {
        this.coorY = coorY;
    }

    public List<Floor> getListFloor() {
        return listFloor;
    }

    public void setListFloor(List<Floor> listFloor) {
        this.listFloor = listFloor;
    }
}

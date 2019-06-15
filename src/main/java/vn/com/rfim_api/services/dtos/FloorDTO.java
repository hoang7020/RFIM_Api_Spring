package vn.com.rfim_api.services.dtos;

public class FloorDTO {
    private String floorId;
    private int floorNo;
    private String description;
    private String shelfId;

    public FloorDTO() {
    }

    public FloorDTO(String floorId, int floorNo, String description, String shelfId) {
        this.floorId = floorId;
        this.floorNo = floorNo;
        this.description = description;
        this.shelfId = shelfId;
    }

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

    public String getShelfId() {
        return shelfId;
    }

    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }
}

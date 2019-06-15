package vn.com.rfim_api.services.dtos;

public class CellDTO {

    private String cellId;
    private int cellNo;
    private String cellRfid;
    private String Description;
    private String floorId;

    public CellDTO() {
    }

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
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }
}

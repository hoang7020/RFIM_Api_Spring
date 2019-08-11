package vn.com.rfim_api.services.jsonobjects;

import java.sql.Date;

public class CellInfo {
    private String cellId;
    private Date date;
    private int quantity;

    public CellInfo() {
    }

    public CellInfo(String cellId, Date date, int quantity) {
        this.cellId = cellId;
        this.date = date;
        this.quantity = quantity;
    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

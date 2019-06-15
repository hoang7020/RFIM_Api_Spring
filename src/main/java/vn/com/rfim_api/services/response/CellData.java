package vn.com.rfim_api.services.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CellData {

    private Object data;

    public CellData() {
    }

    public CellData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    @JsonProperty("cells")
    public void setData(Object data) {
        this.data = data;
    }
}

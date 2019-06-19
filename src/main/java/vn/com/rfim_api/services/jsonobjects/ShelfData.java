package vn.com.rfim_api.services.jsonobjects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShelfData {
    private Object data;

    public ShelfData() {
    }

    public ShelfData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    @JsonProperty("shelves")
    public void setData(Object data) {
        this.data = data;
    }
}

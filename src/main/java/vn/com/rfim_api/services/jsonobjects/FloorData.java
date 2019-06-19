package vn.com.rfim_api.services.jsonobjects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FloorData {

    private Object data;

    public FloorData() {
    }

    public FloorData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    @JsonProperty("floors")
    public void setData(Object data) {
        this.data = data;
    }

}

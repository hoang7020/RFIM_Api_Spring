package vn.com.rfim_api.services.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryData {

    private Object data;

    public CategoryData() {
    }

    public CategoryData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    @JsonProperty("categroies")
    public void setData(Object data) {
        this.data = data;
    }
}

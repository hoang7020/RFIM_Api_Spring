package vn.com.rfim_api.services.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductData {


    private Object data;

    public ProductData() {
    }

    public ProductData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    @JsonProperty("products")
    public void setData(Object data) {
        this.data = data;
    }

}

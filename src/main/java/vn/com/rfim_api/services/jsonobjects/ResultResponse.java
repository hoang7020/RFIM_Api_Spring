package vn.com.rfim_api.services.jsonobjects;

public class ResultResponse {
   private String message;
   private Object data;

    public ResultResponse() {
    }

    public ResultResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}


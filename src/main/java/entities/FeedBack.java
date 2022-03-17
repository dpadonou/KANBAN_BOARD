package entities;

import java.io.Serializable;

public class FeedBack implements Serializable {
    private String message;
    public FeedBack() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FeedBack(String message) {
        this.message = message;
    }
}

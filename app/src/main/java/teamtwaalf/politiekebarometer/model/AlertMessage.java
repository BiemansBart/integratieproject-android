package teamtwaalf.politiekebarometer.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by biema on 24/05/2018.
 */

public class AlertMessage{
    private String Message;


    public AlertMessage(String message) {
        Message = message;
    }

    public String getMessage() {
        return Message;
    }
    public void setMessage(String message) {
        Message = message;
    }


}

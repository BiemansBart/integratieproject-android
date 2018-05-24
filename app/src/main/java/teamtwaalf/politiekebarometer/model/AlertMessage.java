package teamtwaalf.politiekebarometer.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by biema on 24/05/2018.
 */

public class AlertMessage{
    private String Message;
    private LocalDate Date;

    public AlertMessage(String message, LocalDate date) {
        Message = message;
        Date = date;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }
}

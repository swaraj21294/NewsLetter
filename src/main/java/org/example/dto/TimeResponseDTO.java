package org.example.dto;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeResponseDTO {
    private String currentTime;
    private String timezone;

    public TimeResponseDTO(ZonedDateTime zonedDateTime) {
        this.currentTime = zonedDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        this.timezone = zonedDateTime.getZone().getId();
    }
    
    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
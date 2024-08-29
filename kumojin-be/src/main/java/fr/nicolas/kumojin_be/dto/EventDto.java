package fr.nicolas.kumojin_be.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.nicolas.kumojin_be.helper.format.OffsetDateTime.OffsetDateTimeConstants;

import java.time.OffsetDateTime;

public class EventDto {

    private Long id;
    private String name;
    private String description;
    @JsonFormat(pattern = OffsetDateTimeConstants.DATETIME_TIMEZONE_FORMAT)
    private OffsetDateTime startDate;
    @JsonFormat(pattern = OffsetDateTimeConstants.DATETIME_TIMEZONE_FORMAT)
    private OffsetDateTime endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
        this.endDate = endDate;
    }
}

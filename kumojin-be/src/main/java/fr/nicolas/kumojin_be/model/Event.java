package fr.nicolas.kumojin_be.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.nicolas.kumojin_be.helper.format.OffsetDateTime.OffsetDateTimeConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull
    @Column(length = 32)
    private String name;

    private String description;

    @NotNull
    @JsonFormat(pattern = OffsetDateTimeConstants.DATETIME_TIMEZONE_FORMAT)
    private OffsetDateTime startDate;

    @NotNull
    @JsonFormat(pattern = OffsetDateTimeConstants.DATETIME_TIMEZONE_FORMAT)
    private OffsetDateTime endDate;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
        this.endDate = endDate;
    }

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

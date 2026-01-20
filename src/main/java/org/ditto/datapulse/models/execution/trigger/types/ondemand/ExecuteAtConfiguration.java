package org.ditto.datapulse.models.execution.trigger.types.ondemand;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
//TODO: Check why Record is suggested
public class ExecuteAtConfiguration {
    private final String format;
    private final LocalDateTime time;

    @JsonCreator
    public ExecuteAtConfiguration(
            @JsonProperty("format") String format,
            @JsonProperty("time") String time) {
        this.format = format;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.format);
        this.time = LocalDateTime.parse(time, formatter);
    }
}

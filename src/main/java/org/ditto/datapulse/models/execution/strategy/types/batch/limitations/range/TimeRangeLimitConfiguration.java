package org.ditto.datapulse.models.execution.strategy.types.batch.limitations.range;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Data
public class TimeRangeLimitConfiguration {
    private final String format;
    private final ChronoUnit segmentation;
    private final LocalDateTime from;
    private final LocalDateTime to;

    @JsonCreator
    public TimeRangeLimitConfiguration(
            @JsonProperty("format") String format,
            @JsonProperty("segmentation") String segmentation,
            @JsonProperty("from") String from,
            @JsonProperty("to") String to) {
        this.format = format;
        this.segmentation = ChronoUnit.valueOf(segmentation.toUpperCase());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.format);
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }
}

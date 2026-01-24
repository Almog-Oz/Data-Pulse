package org.ditto.datapulse.models.execution.strategy.types.stream.limitations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ditto.datapulse.models.execution.strategy.types.common.LimitsConfiguration;
import org.ditto.datapulse.models.execution.strategy.types.common.ResponseSizeLimitConfiguration;

import java.time.Duration;

@Data
@EqualsAndHashCode(callSuper = true)
public class StreamLimitsConfiguration extends LimitsConfiguration {
    private final Duration duration;
    private final Integer entitiesCount;

    @JsonCreator
    public StreamLimitsConfiguration(@JsonProperty("size") ResponseSizeLimitConfiguration sizeLimitConfiguration,
                                     @JsonProperty("duration") Duration duration,
                                     @JsonProperty("entities-count") Integer count) {
        super(sizeLimitConfiguration);
        this.duration = duration;
        this.entitiesCount = count;
    }
}

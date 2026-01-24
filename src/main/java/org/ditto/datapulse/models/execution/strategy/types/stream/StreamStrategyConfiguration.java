package org.ditto.datapulse.models.execution.strategy.types.stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ditto.datapulse.models.execution.strategy.ExecutionStrategyConfiguration;
import org.ditto.datapulse.models.execution.strategy.types.StrategyType;
import org.ditto.datapulse.models.execution.strategy.types.stream.limitations.StreamLimitsConfiguration;

@Data
@EqualsAndHashCode(callSuper = true)
public class StreamStrategyConfiguration extends ExecutionStrategyConfiguration {
    private final StreamLimitsConfiguration limitations;

    @JsonCreator
    public StreamStrategyConfiguration(@JsonProperty("limitations") StreamLimitsConfiguration limitations) {
        super(StrategyType.STREAM);
        this.limitations = limitations;
    }
}

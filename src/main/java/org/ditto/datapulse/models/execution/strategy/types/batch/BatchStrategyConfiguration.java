package org.ditto.datapulse.models.execution.strategy.types.batch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.ditto.datapulse.models.execution.strategy.ExecutionStrategyConfiguration;
import org.ditto.datapulse.models.execution.strategy.types.StrategyType;
import org.ditto.datapulse.models.execution.strategy.types.batch.limitations.BatchLimitsConfiguration;

@Getter
@EqualsAndHashCode(callSuper = true)
public class BatchStrategyConfiguration extends ExecutionStrategyConfiguration {
    private final BatchLimitsConfiguration limitations;

    @JsonCreator
    public BatchStrategyConfiguration(@JsonProperty("limitations") BatchLimitsConfiguration limitations) {
        super(StrategyType.BATCH);
        this.limitations = limitations;
    }
}

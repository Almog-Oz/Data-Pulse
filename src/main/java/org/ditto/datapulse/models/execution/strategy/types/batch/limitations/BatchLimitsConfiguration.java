package org.ditto.datapulse.models.execution.strategy.types.batch.limitations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ditto.datapulse.models.execution.strategy.types.batch.limitations.count.EntitiesCountBatchLimitConfiguration;
import org.ditto.datapulse.models.execution.strategy.types.batch.limitations.range.TimeRangeLimitConfiguration;
import org.ditto.datapulse.models.execution.strategy.types.common.LimitsConfiguration;
import org.ditto.datapulse.models.execution.strategy.types.common.ResponseSizeLimitConfiguration;

@Data
@EqualsAndHashCode(callSuper = true)
public class BatchLimitsConfiguration extends LimitsConfiguration {
    private TimeRangeLimitConfiguration timeRange;
    private EntitiesCountBatchLimitConfiguration entitiesCount;

    @JsonCreator
    public BatchLimitsConfiguration(
            @JsonProperty("size") ResponseSizeLimitConfiguration sizeLimitConfiguration,
            @JsonProperty("time-range") TimeRangeLimitConfiguration timeRangeLimitConfiguration,
            @JsonProperty("entities-count") EntitiesCountBatchLimitConfiguration entitiesCountBatchLimitConfiguration
    ) {
        super(sizeLimitConfiguration);
        this.timeRange = timeRangeLimitConfiguration;
        this.entitiesCount = entitiesCountBatchLimitConfiguration;
    }
}

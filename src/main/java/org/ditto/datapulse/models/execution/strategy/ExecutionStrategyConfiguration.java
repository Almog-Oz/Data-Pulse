package org.ditto.datapulse.models.execution.strategy;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.ditto.datapulse.models.execution.strategy.types.StrategyType;
import org.ditto.datapulse.models.execution.strategy.types.batch.BatchStrategyConfiguration;
import org.ditto.datapulse.models.execution.strategy.types.stream.StreamStrategyConfiguration;

@Data
@AllArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BatchStrategyConfiguration.class, name = "batch"),
        @JsonSubTypes.Type(value = StreamStrategyConfiguration.class, name = "stream"),
})
public abstract class ExecutionStrategyConfiguration {
    private final StrategyType type;
}

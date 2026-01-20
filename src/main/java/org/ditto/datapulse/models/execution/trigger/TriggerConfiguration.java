package org.ditto.datapulse.models.execution.trigger;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.ditto.datapulse.models.execution.trigger.types.ExecutionType;
import org.ditto.datapulse.models.execution.trigger.types.interval.IntervalTriggerConfiguration;
import org.ditto.datapulse.models.execution.trigger.types.ondemand.OnDemandTriggerConfiguration;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OnDemandTriggerConfiguration.class, name = "on-demand"),
        @JsonSubTypes.Type(value = IntervalTriggerConfiguration.class, name = "interval"),
})
@AllArgsConstructor
public abstract class TriggerConfiguration {
    private final ExecutionType type;
}

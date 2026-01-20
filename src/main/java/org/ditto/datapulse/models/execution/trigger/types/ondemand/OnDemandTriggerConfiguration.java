package org.ditto.datapulse.models.execution.trigger.types.ondemand;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ditto.datapulse.models.execution.trigger.TriggerConfiguration;
import org.ditto.datapulse.models.execution.trigger.types.ExecutionType;

@Data
@EqualsAndHashCode(callSuper = true)
public class OnDemandTriggerConfiguration extends TriggerConfiguration {
    private final ExecuteAtConfiguration executeAt;

    @JsonCreator
    public OnDemandTriggerConfiguration(@JsonProperty("execute-at") ExecuteAtConfiguration executeAt) {
        super(ExecutionType.ON_DEMAND);
        this.executeAt = executeAt;
    }
}

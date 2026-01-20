package org.ditto.datapulse.models.execution.trigger.types.interval;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ditto.datapulse.models.execution.trigger.TriggerConfiguration;
import org.ditto.datapulse.models.execution.trigger.types.ExecutionType;
import org.springframework.scheduling.support.CronExpression;

@Data
@EqualsAndHashCode(callSuper = true)
public class IntervalTriggerConfiguration extends TriggerConfiguration {
    private final CronExpression schedule;

    @JsonCreator
    public IntervalTriggerConfiguration(@JsonProperty("schedule") String cronjobExpression) {
        super(ExecutionType.INTERVAL);
        this.schedule = CronExpression.parse(cronjobExpression);
    }
}

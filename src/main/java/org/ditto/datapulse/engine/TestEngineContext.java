package org.ditto.datapulse.engine;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.ditto.datapulse.models.execution.trigger.TriggerConfiguration;
import org.ditto.datapulse.models.tests.DQTestType;

@Data
@RequiredArgsConstructor
public class TestEngineContext {
    private final DQTestType supportedTestType;
    private final String datasource;
    private final String sink;
    private final TriggerConfiguration triggerConfiguration;

    public String generateIdentifier() {
        return  this.supportedTestType.name() + this.datasource + this.sink;
    }
}

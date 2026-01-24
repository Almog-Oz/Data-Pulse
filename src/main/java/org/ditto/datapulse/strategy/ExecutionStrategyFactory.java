package org.ditto.datapulse.strategy;

import org.ditto.datapulse.models.execution.strategy.ExecutionStrategyConfiguration;
import org.ditto.datapulse.models.tests.DQTest;
import org.ditto.datapulse.models.tests.DQTestType;
import org.ditto.datapulse.strategy.creator.ExecutionStrategyCreator;

import java.util.List;

public class ExecutionStrategyFactory {
    private final List<ExecutionStrategyCreator> creators;

    public ExecutionStrategyFactory() {


        this.creators = List.of();
    }

    public <DQTestT extends DQTest> ExecutionStrategy<DQTestT> create(ExecutionStrategyConfiguration configuration, DQTestType type) {
        ExecutionStrategyCreator creator = this.creators.stream()
                .filter(candidate -> candidate.isCompatible(configuration.getType(), type))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No compatible creator was found"));

        return creator.create(configuration);
    }
}

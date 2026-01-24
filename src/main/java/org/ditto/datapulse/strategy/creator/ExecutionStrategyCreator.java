package org.ditto.datapulse.strategy.creator;

import org.ditto.datapulse.models.execution.strategy.ExecutionStrategyConfiguration;
import org.ditto.datapulse.models.execution.strategy.types.StrategyType;
import org.ditto.datapulse.models.tests.DQTest;
import org.ditto.datapulse.models.tests.DQTestType;
import org.ditto.datapulse.strategy.ExecutionStrategy;

public interface ExecutionStrategyCreator {
    <DQTestT extends DQTest> ExecutionStrategy<DQTestT> create(ExecutionStrategyConfiguration strategyConfiguration);

    Boolean isCompatible(StrategyType strategyType, DQTestType testType);
}

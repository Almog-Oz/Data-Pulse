package org.ditto.datapulse.strategy;

import org.ditto.datapulse.models.tests.DQTest;

public interface ExecutionStrategy<DQTestT extends DQTest> {
    void execute(DQTestT test);
}

package org.ditto.datapulse.engine;

import lombok.Getter;
import org.ditto.datapulse.models.tests.DQTest;
import org.ditto.datapulse.strategy.ExecutionStrategy;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TestEngine<DQTestT extends DQTest> {
    private final ExecutionStrategy<DQTestT> strategy;
    private final TestEngineContext context;
    private final List<DQTestT> tests;

    public TestEngine(ExecutionStrategy<DQTestT> strategy, TestEngineContext context) {
        this.strategy = strategy;
        this.context = context;
        this.tests = new ArrayList<>();
    }

    public void run() {
        this.tests.forEach(this.strategy::execute);
    }

    public void subscribe(DQTestT test) {
        this.tests.add(test);
    }
}

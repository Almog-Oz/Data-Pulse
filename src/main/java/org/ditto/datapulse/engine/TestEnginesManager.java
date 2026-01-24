package org.ditto.datapulse.engine;

import org.ditto.datapulse.models.tests.DQTest;
import org.ditto.datapulse.strategy.ExecutionStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TestEnginesManager {
    private final Map<String, TestEngine<? extends DQTest>> engines;

    public TestEnginesManager() {
        this.engines = new HashMap<>();
    }

    public <DQTestT extends DQTest> TestEngine<DQTestT> create(ExecutionStrategy<DQTestT> strategy, TestEngineContext context) {
        TestEngine<DQTestT> engine = new TestEngine<>(strategy, context);

        this.engines.put(context.generateIdentifier(), engine);

        return engine;
    }

    public Optional<TestEngine<? extends DQTest>> get(TestEngineContext context) {
        return Optional.ofNullable(this.engines.get(context.generateIdentifier()));
    }
}

package org.ditto.datapulse.strategy;

import lombok.RequiredArgsConstructor;
import org.ditto.datapulse.models.tests.DQTest;

import java.util.List;

@RequiredArgsConstructor
public class TestsRunner<DQTestT extends DQTest> {
    private final ExecutionStrategy<DQTestT> strategy;
    private final List<DQTestT> tests;


    public void run() {
        this.tests.forEach(this.strategy::execute);
    }
}

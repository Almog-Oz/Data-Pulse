package org.ditto.datapulse.models.tests;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DQTest {
    private final DQTestType type;
    private final String name;
    private final String description;
}

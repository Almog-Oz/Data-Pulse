package org.ditto.datapulse.models.tests;

import lombok.Getter;

@Getter
public class SQLTest extends DQTest {
    private final String query;

    public SQLTest(String name, String description, String query) {
        super(
                DQTestType.SQL,
                name,
                description
        );

        this.query = query;
    }
}

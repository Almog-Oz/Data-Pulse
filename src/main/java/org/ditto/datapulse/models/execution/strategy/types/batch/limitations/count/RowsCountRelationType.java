package org.ditto.datapulse.models.execution.strategy.types.batch.limitations.count;

import lombok.Getter;

public enum RowsCountRelationType {
    FIRST("first"),
    LAST("last");

    private final String value;

    RowsCountRelationType(String value) {
        this.value = value;
    }

    /**
     * Returns the enum corresponding to the given value.
     * @param value the string representation of the enum
     * @return the matching RowsCountRelationType
     * @throws IllegalArgumentException if no match is found
     */
    public static RowsCountRelationType fromJSONValue(String value) {
        for (RowsCountRelationType type : RowsCountRelationType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown RowsCountRelationType value: " + value);
    }
}

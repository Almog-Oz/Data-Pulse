package org.ditto.datapulse.models.execution.strategy.types.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
public abstract class LimitsConfiguration {
    private ResponseSizeLimitConfiguration size;
}

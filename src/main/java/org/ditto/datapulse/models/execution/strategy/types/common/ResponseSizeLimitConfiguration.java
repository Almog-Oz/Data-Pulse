package org.ditto.datapulse.models.execution.strategy.types.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;


@Getter
public class ResponseSizeLimitConfiguration {
    private final DataSize value;

    @JsonCreator
    public ResponseSizeLimitConfiguration(@JsonProperty("amount") long amount,
                                          @JsonProperty("unit") String dataUnitSuffix) {
        this.value = DataSize.of(amount, DataUnit.fromSuffix(dataUnitSuffix));
    }
}

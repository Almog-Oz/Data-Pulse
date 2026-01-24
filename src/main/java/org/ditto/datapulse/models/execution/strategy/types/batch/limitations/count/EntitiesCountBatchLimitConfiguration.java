package org.ditto.datapulse.models.execution.strategy.types.batch.limitations.count;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EntitiesCountBatchLimitConfiguration {
    private final String orderByField;
    private final Integer value;
    private final RowsCountRelationType relation;

    @JsonCreator
    public EntitiesCountBatchLimitConfiguration(@JsonProperty("order-by-field") String orderByField,
                                                @JsonProperty("value") Integer value,
                                                @JsonProperty("relation") String relation) {
        this.orderByField = orderByField;
        this.value = value;
        this.relation = RowsCountRelationType.fromJSONValue(relation);
    }
}

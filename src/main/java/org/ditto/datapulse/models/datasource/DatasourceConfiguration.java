package org.ditto.datapulse.models.datasource;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.ditto.datapulse.models.datasource.types.DatasourceType;

@Data
@RequiredArgsConstructor
public abstract class DatasourceConfiguration {
    private final DatasourceType type;
}

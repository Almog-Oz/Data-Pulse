package org.ditto.datapulse.models.datasource.types;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ditto.datapulse.models.datasource.DatasourceConfiguration;
import org.ditto.datapulse.models.execution.strategy.types.batch.limitations.BatchLimitsConfiguration;

@Data
@EqualsAndHashCode(callSuper = true)
public class SQLDatasourceConfiguration extends DatasourceConfiguration {
    private final String url;
    private final String username;
    private final String password;
    private final String sourceQuery;
    private final BatchLimitsConfiguration limitations;

    public SQLDatasourceConfiguration(String url,
                                      String username,
                                      String password,
                                      String sourceQuery,
                                      BatchLimitsConfiguration limitations) {
        super(DatasourceType.ORACLE);
        this.url = url;
        this.username = username;
        this.password = password;
        this.sourceQuery = sourceQuery;
        this.limitations = limitations;
    }
}

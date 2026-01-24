package org.ditto.datapulse.strategy.resolver.sql;

import lombok.RequiredArgsConstructor;
import org.ditto.datapulse.conntector.oracle.CommonOracleBatchRequestContext;
import org.ditto.datapulse.models.datasource.types.SQLDatasourceConfiguration;
import org.ditto.datapulse.models.execution.strategy.types.batch.limitations.BatchLimitsConfiguration;
import org.ditto.datapulse.models.tests.SQLTest;
import org.ditto.datapulse.strategy.resolver.QueryResolver;

@RequiredArgsConstructor
public class SQLQueryResolver implements QueryResolver<SQLTest, SQLDatasourceConfiguration, CommonOracleBatchRequestContext> {
    private final BatchLimitsConfiguration limitsConfiguration;

    @Override
    public CommonOracleBatchRequestContext resolve(SQLTest test, SQLDatasourceConfiguration datasourceConfiguration) {
        String template = test.getQuery();

        // Here we will transform the query according to the limitations and the datasource query
//        this.limitsConfiguration.getTimeRange();
//        this.limitsConfiguration.getEntitiesCount();
//        this.limitsConfiguration.getSize();

        String finalQuery = template.replace("{data-sample}", "(" + datasourceConfiguration.getSourceQuery() + ")");

        return new CommonOracleBatchRequestContext(finalQuery);
    }
}

package org.ditto.datapulse.strategy.creator;

import org.ditto.datapulse.conntector.oracle.OracleConnector;
import org.ditto.datapulse.models.datasource.types.SQLDatasourceConfiguration;
import org.ditto.datapulse.models.execution.strategy.ExecutionStrategyConfiguration;
import org.ditto.datapulse.models.execution.strategy.types.StrategyType;
import org.ditto.datapulse.models.execution.strategy.types.batch.BatchStrategyConfiguration;
import org.ditto.datapulse.models.tests.DQTest;
import org.ditto.datapulse.models.tests.DQTestType;
import org.ditto.datapulse.models.tests.SQLTest;
import org.ditto.datapulse.sink.ElasticsearchSinkConnector;
import org.ditto.datapulse.sink.SinkConnector;
import org.ditto.datapulse.strategy.BatchExecutionStrategy;
import org.ditto.datapulse.strategy.ExecutionStrategy;
import org.ditto.datapulse.strategy.resolver.sql.SQLQueryResolver;

public class SQLBatchExecutionStrategyCreator implements ExecutionStrategyCreator {
    private final SQLDatasourceConfiguration configuration;
    private final SQLQueryResolver sqlQueryResolver;
    private final SinkConnector sink;



    @Override
    public <DQTestT extends DQTest> ExecutionStrategy<DQTestT> create(ExecutionStrategyConfiguration strategyConfiguration) {
        if (strategyConfiguration instanceof BatchStrategyConfiguration batchStrategyConfiguration) {
            SQLQueryResolver sqlQueryResolver = new SQLQueryResolver(batchStrategyConfiguration.getLimitations());

        }

        OracleConnector oracleConnector = new OracleConnector(this.configuration);
        ElasticsearchSinkConnector mockedElasticsearchSink = new ElasticsearchSinkConnector();

        ExecutionStrategy<SQLTest> executionStrategy = new BatchExecutionStrategy<>(ora);
        return null;
    }

    @Override
    public Boolean isCompatible(StrategyType strategyType, DQTestType testType) {
        return strategyType == StrategyType.BATCH && testType == DQTestType.SQL;
    }
}

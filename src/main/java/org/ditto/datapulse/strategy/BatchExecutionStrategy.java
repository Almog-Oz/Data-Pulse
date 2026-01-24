package org.ditto.datapulse.strategy;

import lombok.RequiredArgsConstructor;
import org.ditto.datapulse.conntector.BatchConnector;
import org.ditto.datapulse.models.datasource.DatasourceConfiguration;
import org.ditto.datapulse.models.tests.DQTest;
import org.ditto.datapulse.sink.SinkConnector;
import org.ditto.datapulse.strategy.resolver.QueryResolver;

@RequiredArgsConstructor
public class BatchExecutionStrategy<DQTestT extends DQTest, RequestContextT, ResultT, DatasourceConfigurationT extends DatasourceConfiguration>
        implements ExecutionStrategy<DQTestT> {
    private final BatchConnector<DatasourceConfigurationT, RequestContextT, ResultT> sourceConnector;
    private final QueryResolver<DQTestT, DatasourceConfigurationT, RequestContextT> resolver;
    private final SinkConnector sink;

    @Override
    public void execute(DQTestT test) {
        RequestContextT ctx = this.resolver.resolve(test, this.sourceConnector.getDatasourceConfiguration());

        ResultT result = this.sourceConnector.execute(ctx);

        this.sink.write(result);
    }

}

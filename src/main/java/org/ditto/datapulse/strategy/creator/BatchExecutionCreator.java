package org.ditto.datapulse.strategy.creator;

import org.ditto.datapulse.conntector.BatchConnector;
import org.ditto.datapulse.conntector.SourceConnector;
import org.ditto.datapulse.conntector.SourceConnectorFactory;
import org.ditto.datapulse.models.datasource.DatasourceConfiguration;
import org.ditto.datapulse.sink.SinkConnectorFactory;

public abstract class BatchExecutionCreator implements ExecutionStrategyCreator {
    private final SourceConnectorFactory sourceConnectorFactory;
    private final SinkConnectorFactory sinkConnectorFactory;

    public BatchExecutionCreator() {
        this.sourceConnectorFactory = new SourceConnectorFactory();
        this.sinkConnectorFactory = new SinkConnectorFactory();
    }

    protected <RequestT, ResultT>  BatchConnector<RequestT, ResultT> createSourceConnector(DatasourceConfiguration configuration) {
        return this.sourceConnectorFactory.create(configuration);
    }
}

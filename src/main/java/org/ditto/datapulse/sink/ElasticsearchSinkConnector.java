package org.ditto.datapulse.sink;

import org.ditto.datapulse.sink.types.SinkType;

public class ElasticsearchSinkConnector implements SinkConnector{
    @Override
    public <T> void write(T executionResult) {
        System.out.println("Writing result to elasticsearch");
        System.out.println(executionResult);
        return;
    }

    @Override
    public Boolean testConnection() {
        return true;
    }

    @Override
    public SinkType getSinkType() {
        return SinkType.ELASTICSEARCH;
    }
}

package org.ditto.datapulse.sink;

import org.ditto.datapulse.models.sink.SinkConfiguration;

public class SinkConnectorFactory {
    public SinkConnector create(SinkConfiguration configuration) {
        return new ElasticsearchSinkConnector();
    }
}

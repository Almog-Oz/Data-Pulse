package org.ditto.datapulse.sink;

import org.ditto.datapulse.models.sink.types.SinkType;

public interface SinkConnector {
    <T> void write(T executionResult);
    Boolean testConnection();
    SinkType getSinkType();
}

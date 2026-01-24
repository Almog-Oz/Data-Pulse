package org.ditto.datapulse.models.sink.types;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ditto.datapulse.models.sink.SinkConfiguration;

@Data
@EqualsAndHashCode(callSuper = true)
public class ElasticsearchSinkConfiguration extends SinkConfiguration {
    private final String host;
    private final String index;

    public ElasticsearchSinkConfiguration(String host,
                                          String index) {
        super(SinkType.ELASTICSEARCH);
        this.host = host;
        this.index = index;
    }
}

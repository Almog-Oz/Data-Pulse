package org.ditto.datapulse.models.sink;

import lombok.RequiredArgsConstructor;
import org.ditto.datapulse.models.sink.types.SinkType;

@RequiredArgsConstructor
public abstract class SinkConfiguration {
    private final SinkType type;
}

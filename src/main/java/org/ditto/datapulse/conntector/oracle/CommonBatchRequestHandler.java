package org.ditto.datapulse.conntector.oracle;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommonBatchRequestHandler extends OracleBatchRequestHandler<List<Map<String, Object>>> {
    private final String query;

    public CommonBatchRequestHandler(String query) {
        super(query, new MapListHandler());

        this.query = query;
    }
}

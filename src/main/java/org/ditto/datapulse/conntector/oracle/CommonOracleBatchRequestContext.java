package org.ditto.datapulse.conntector.oracle;

import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.List;
import java.util.Map;


public class CommonOracleBatchRequestContext extends OracleBatchRequestContext<List<Map<String, Object>>> {
    public CommonOracleBatchRequestContext(String query) {
        super(query, new MapListHandler());
    }
}

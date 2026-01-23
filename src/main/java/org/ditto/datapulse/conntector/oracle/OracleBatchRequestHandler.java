package org.ditto.datapulse.conntector.oracle;

import lombok.AllArgsConstructor;
import org.apache.commons.dbutils.ResultSetHandler;

@AllArgsConstructor
public class OracleBatchRequestHandler<T> {
    private String query;
    private ResultSetHandler<T> resultSetHandler;
}

package org.ditto.datapulse.conntector.oracle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.dbutils.ResultSetHandler;

@Getter
@AllArgsConstructor
public class OracleBatchRequestContext<T> {
    private String query;
    private ResultSetHandler<T> resultSetHandler;
}

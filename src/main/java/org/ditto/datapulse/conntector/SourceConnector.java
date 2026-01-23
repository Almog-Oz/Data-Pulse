package org.ditto.datapulse.conntector;

import java.sql.SQLException;

public interface SourceConnector {
    Boolean testConnection() throws SQLException;
    DatasourceType getDatasourceType();
    void close();
}

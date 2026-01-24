package org.ditto.datapulse.conntector;

import org.ditto.datapulse.models.datasource.types.DatasourceType;

import java.sql.SQLException;

public interface SourceConnector {
    Boolean testConnection() throws SQLException;
    DatasourceType getDatasourceType();
    void close();
}

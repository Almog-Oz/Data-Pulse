package org.ditto.datapulse.conntector;

import org.ditto.datapulse.conntector.oracle.OracleConnector;
import org.ditto.datapulse.models.datasource.DatasourceConfiguration;
import org.ditto.datapulse.models.datasource.types.SQLDatasourceConfiguration;

public class SourceConnectorFactory {

    public BatchConnector<?, ?> create(DatasourceConfiguration configuration) {

        return new OracleConnector((SQLDatasourceConfiguration) configuration);
    }

}

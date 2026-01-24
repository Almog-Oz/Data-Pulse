package org.ditto.datapulse.conntector;

import org.ditto.datapulse.models.datasource.DatasourceConfiguration;

public interface BatchConnector<
        RequestContextT,
        ResultT>
        extends SourceConnector {

    ResultT execute(RequestContextT cxt);
}

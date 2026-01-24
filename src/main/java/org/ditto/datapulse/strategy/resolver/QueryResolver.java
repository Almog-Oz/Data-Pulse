package org.ditto.datapulse.strategy.resolver;

import org.ditto.datapulse.models.datasource.DatasourceConfiguration;
import org.ditto.datapulse.models.tests.DQTest;

public interface QueryResolver<DQTestT extends DQTest, DatasourceConfigurationT extends DatasourceConfiguration, RequestContextT> {
    RequestContextT resolve(DQTestT test, DatasourceConfigurationT datasourceConfiguration);
}

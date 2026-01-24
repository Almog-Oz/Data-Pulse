package org.ditto.datapulse.conntector.oracle;

import lombok.Getter;
import org.apache.commons.dbutils.QueryRunner;
import org.ditto.datapulse.conntector.BatchConnector;
import org.ditto.datapulse.models.datasource.types.DatasourceType;
import org.ditto.datapulse.models.datasource.types.SQLDatasourceConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Getter
public class OracleConnector implements BatchConnector<CommonOracleBatchRequestContext, List<Map<String, Object>>> {
    private final SQLDatasourceConfiguration configuration;
    private final Connection connection;

    public OracleConnector(SQLDatasourceConfiguration configuration) {
        this.configuration = configuration;
        try {
            this.connection = DriverManager.getConnection(
                    configuration.getUrl(),
                    configuration.getUsername(),
                    configuration.getPassword());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Map<String, Object>> execute(CommonOracleBatchRequestContext ctx) {
        System.out.println("Querying oracle database");
        List<Map<String, Object>> result;
        try {
            QueryRunner queryRunner = new QueryRunner();
            result = queryRunner.query(this.connection, ctx.getQuery(), ctx.getResultSetHandler());
        } catch (SQLException se) {
            throw new RuntimeException("Couldn't query the database.", se);
        }

        return result;
    }

    @Override
    public Boolean testConnection() {
        try {
            System.out.println("Pinging Oracle database");
            return !this.connection.isClosed();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DatasourceType getDatasourceType() {
        return DatasourceType.ORACLE;
    }

    @Override
    public void close() {
        try {
            System.out.println("Closing connection to database");
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

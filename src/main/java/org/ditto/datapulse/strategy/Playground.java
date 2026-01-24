package org.ditto.datapulse.strategy;

import org.ditto.datapulse.conntector.oracle.CommonOracleBatchRequestContext;
import org.ditto.datapulse.conntector.oracle.OracleConnector;
import org.ditto.datapulse.models.tests.SQLTest;
import org.ditto.datapulse.sink.ElasticsearchSinkConnector;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Playground {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@//localhost:1521/FREE";
        String username = "system";
        String password = "password1";

        OracleConnector datasource = new OracleConnector(url, username, password);
        Function<SQLTest, CommonOracleBatchRequestContext> converter = Playground::convert;
        ElasticsearchSinkConnector mockedElasticsearchConnector = new ElasticsearchSinkConnector();

        BatchExecutionStrategy<CommonOracleBatchRequestContext, List<Map<String, Object>>, SQLTest> oracleExecutionStrategy =
                new BatchExecutionStrategy<>(datasource, converter, mockedElasticsearchConnector);

        String countQuery = """
                SELECT
                  SUM(CASE WHEN pickup_datetime IS NOT NULL THEN 1 ELSE 0 END) AS non_null_pickup_dt
                FROM (SELECT * FROM YELLOW_TRIPDATA_SAMPLE);
                """;
        SQLTest countSQLTest = new SQLTest("Existing Pickup Time", "Checks the rows count with pickup datetime", countQuery);

        List<SQLTest> tests = List.of(countSQLTest);
        TestsRunner<SQLTest> sqlTestsRunner = new TestsRunner<>(oracleExecutionStrategy, tests);

        sqlTestsRunner.run();
    }

    public static CommonOracleBatchRequestContext convert(SQLTest test) {
        return new CommonOracleBatchRequestContext(test.getQuery());
    }
}

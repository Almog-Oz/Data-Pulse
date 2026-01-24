package org.ditto.datapulse.conntector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ditto.datapulse.conntector.oracle.CommonOracleBatchRequestContext;
import org.ditto.datapulse.conntector.oracle.OracleConnector;

import java.util.List;
import java.util.Map;

public class Playground {
    public static void main(String[] args) throws JsonProcessingException {
        String url = "jdbc:oracle:thin:@//localhost:1521/FREE";
        String username = "system";
        String password = "password1";
        OracleConnector connector = new OracleConnector(url, username, password);
        System.out.println("Is active: " + connector.testConnection());

        String countQuery = """
                SELECT
                  SUM(CASE WHEN pickup_datetime IS NOT NULL THEN 1 ELSE 0 END) AS non_null_pickup_dt
                FROM (SELECT * FROM YELLOW_TRIPDATA_SAMPLE);
                """;
        CommonOracleBatchRequestContext countQueryContext = new CommonOracleBatchRequestContext(countQuery);

        String distinctQuery = """
                SELECT
                    CASE payment_type
                    WHEN 1 THEN 'credit_card'
                    WHEN 2 THEN 'cash'
                    WHEN 3 THEN 'bitcoin'
                    WHEN 4 THEN 'free_of_charge'
                    ELSE 'unknown'\s
                    end as payment_type,
                  COUNT(*) AS rides_count
                FROM (SELECT * FROM YELLOW_TRIPDATA_SAMPLE)
                GROUP BY payment_type;
                """;
        CommonOracleBatchRequestContext distinctQueryContext = new CommonOracleBatchRequestContext(distinctQuery);

        List<Map<String, Object>> countResult = connector.execute(countQueryContext);
        List<Map<String, Object>> distinctResult = connector.execute(distinctQueryContext);
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(countResult));
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(distinctResult));
    }
}

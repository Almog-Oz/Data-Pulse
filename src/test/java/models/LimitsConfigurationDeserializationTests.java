package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ditto.datapulse.models.execution.strategy.types.common.ResponseSizeLimitConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import static org.assertj.core.api.Assertions.assertThat;

class LimitsConfigurationDeserializationTests {
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Should successfully deserialize ResponseSizeLimitConfiguration from valid JSON")
    void shouldDeserializeResponseSizeLimit() throws Exception {
        // language=JSON
        String json = """
                {
                    "amount": 10,
                    "unit": "MB"
                }
                """;

        // Act
        ResponseSizeLimitConfiguration result = this.objectMapper.readValue(json, ResponseSizeLimitConfiguration.class);

        assertThat(result.getValue()).isEqualTo(DataSize.ofMegabytes(10));
    }


    @ParameterizedTest
    @ValueSource(strings = {"B", "KB", "MB", "GB"})
    @DisplayName("Should handle various DataUnit suffixes (B, KB, MB, GB)")
    public void shouldHandleDifferentUnits(String suffix) throws Exception {
        // language=JSON
        String jsonTemplate = """
                {
                  "amount": 1,
                  "unit": "%s"
                }
                """;

        ResponseSizeLimitConfiguration result = this.objectMapper.readValue(String.format(jsonTemplate, suffix), ResponseSizeLimitConfiguration.class);

        assertThat(result.getValue()).isEqualTo(DataSize.of(1, DataUnit.fromSuffix(suffix)));
    }
}
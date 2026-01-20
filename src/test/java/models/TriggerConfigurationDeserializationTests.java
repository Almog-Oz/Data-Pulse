package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ditto.datapulse.models.execution.trigger.TriggerConfiguration;
import org.ditto.datapulse.models.execution.trigger.types.ExecutionType;
import org.ditto.datapulse.models.execution.trigger.types.interval.IntervalTriggerConfiguration;
import org.ditto.datapulse.models.execution.trigger.types.ondemand.OnDemandTriggerConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.support.CronExpression;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//TODO: sanity mostly for now - more tests should be added in future
public class TriggerConfigurationDeserializationTests {
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Should deserialize 'interval' trigger into IntervalTriggerConfiguration")
    public void shouldDeserializeIntervalTrigger() throws Exception {
        // language=JSON
        String json = """
            {
              "type": "interval",
              "schedule": "0 0 * * * *"
            }
            """;

        TriggerConfiguration result = this.objectMapper.readValue(json, TriggerConfiguration.class);
        assertThat(result).isInstanceOf(IntervalTriggerConfiguration.class);

        IntervalTriggerConfiguration interval = (IntervalTriggerConfiguration) result;
        assertThat(interval.getType()).isEqualTo(ExecutionType.INTERVAL);
        assertThat(interval.getSchedule()).isEqualTo(CronExpression.parse("0 0 * * * *"));
    }

    @Test
    @DisplayName("Should deserialize 'on-demand' trigger into OnDemandTriggerConfiguration")
    public void shouldDeserializeOnDemandTrigger() throws Exception {
        // language=json
        String json = """
            
                {
              "type": "on-demand",
              "execute-at": {
                "format": "yyyy-MM-dd HH:mm:ss.SSS",
                "time": "2026-01-20 20:00:00.000"
              }
            }
            """;

        TriggerConfiguration result = this.objectMapper.readValue(json, TriggerConfiguration.class);
        assertThat(result).isInstanceOf(OnDemandTriggerConfiguration.class);

        OnDemandTriggerConfiguration onDemand = (OnDemandTriggerConfiguration) result;
        assertThat(onDemand.getType()).isEqualTo(ExecutionType.ON_DEMAND);
        assertThat(onDemand.getExecuteAt().getFormat()).isEqualTo("yyyy-MM-dd HH:mm:ss.SSS");
        assertThat(onDemand.getExecuteAt().getTime()).isEqualTo(LocalDateTime.of(2026, 1, 20, 20, 0, 0, 0));

    }

    @Test
    @DisplayName("Should fail deserialization when trigger type is unknown")
    public void shouldFailOnUnknownTriggerType() {
        // language=json
        String json = """
            {
              "type": "unknown",
              "someField": "value"
            }
            """;

        Throwable thrown = org.assertj.core.api.Assertions.catchThrowable(() ->
                this.objectMapper.readValue(json, TriggerConfiguration.class)
        );

        assertThat(thrown)
                .isInstanceOf(com.fasterxml.jackson.databind.exc.InvalidTypeIdException.class)
                .hasMessageContaining("unknown");
    }
}

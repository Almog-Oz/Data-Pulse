package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.ditto.datapulse.models.execution.trigger.TriggerConfiguration;
import org.ditto.datapulse.models.execution.trigger.types.ExecutionType;
import org.ditto.datapulse.models.execution.trigger.types.interval.IntervalTriggerConfiguration;
import org.ditto.datapulse.models.execution.trigger.types.ondemand.OnDemandTriggerConfiguration;
import org.ditto.datapulse.utils.JacksonPolymorphicUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

//TODO: Double check needed
public class JacksonPolymorphicUtilsTests {

    @Test
    @DisplayName("Should deserialize an on-demand trigger configuration with execute-at section")
    public void shouldConvertToOnDemandTriggerConfiguration() throws JsonProcessingException {
        Map<String, Object> properties = Map.of(
                "type", "on-demand",
                "execute-at", Map.of(
                        "format", "yyyy-MM-dd HH:mm:ss",
                        "time", "2026-01-20 10:30:00"
                )
        );

        TriggerConfiguration result =
                JacksonPolymorphicUtils.convertToPolymorphicType(
                        properties,
                        TriggerConfiguration.class
                );

        assertThat(result).isInstanceOf(OnDemandTriggerConfiguration.class);
        OnDemandTriggerConfiguration onDemand = (OnDemandTriggerConfiguration) result;

        assertThat(onDemand.getType()).isEqualTo(ExecutionType.ON_DEMAND);
        assertThat(onDemand.getExecuteAt()).isNotNull();
        assertThat(onDemand.getExecuteAt().getFormat()).isEqualTo("yyyy-MM-dd HH:mm:ss");
        assertThat(onDemand.getExecuteAt().getTime()).isEqualTo(LocalDateTime.of(2026, 1, 20, 10, 30));
    }

    @Test
    @DisplayName("Should deserialize an interval trigger configuration with a cron expression")
    public void shouldConvertToIntervalTriggerConfiguration() throws JsonProcessingException {
        Map<String, Object> properties = Map.of(
                "type", "interval",
                "schedule", "0 */15 * * * *"
        );

        TriggerConfiguration result =
                JacksonPolymorphicUtils.convertToPolymorphicType(
                        properties,
                        TriggerConfiguration.class
                );

        assertThat(result).isInstanceOf(IntervalTriggerConfiguration.class);
        IntervalTriggerConfiguration interval = (IntervalTriggerConfiguration) result;
        assertThat(interval.getType()).isEqualTo(ExecutionType.INTERVAL);
        assertThat(interval.getSchedule().toString()).isEqualTo("0 */15 * * * *");
    }

    @Test
    @DisplayName("Should fail when the trigger type is missing")
    public void shouldFailWhenTypeIsMissing() {
        Map<String, Object> properties = Map.of(
                "schedule", "0 */5 * * * *"
        );

        assertThatThrownBy(() ->
                JacksonPolymorphicUtils.convertToPolymorphicType(
                        properties,
                        TriggerConfiguration.class
                )
        ).isInstanceOf(JsonProcessingException.class);
    }

    @Test
    @DisplayName("Should fail when an unknown trigger type is provided")
    public void shouldFailForUnknownTriggerType() {
        Map<String, Object> properties = Map.of(
                "type", "unknown",
                "schedule", "0 */5 * * * *"
        );

        assertThatThrownBy(() ->
                JacksonPolymorphicUtils.convertToPolymorphicType(
                        properties,
                        TriggerConfiguration.class
                )
        ).isInstanceOf(JsonProcessingException.class)
                .hasMessageContaining("unknown");
    }

    @Test
    @DisplayName("Should fail when execute-at date format does not match the provided pattern")
    public void shouldFailWhenExecuteAtDateIsInvalid() {
        Map<String, Object> properties = Map.of(
                "type", "on-demand",
                "execute-at", Map.of(
                        "format", "yyyy-MM-dd HH:mm:ss",
                        "time", "INVALID_DATE"
                )
        );

        assertThatThrownBy(() ->
                JacksonPolymorphicUtils.convertToPolymorphicType(
                        properties,
                        TriggerConfiguration.class
                )
        ).isInstanceOf(JsonProcessingException.class);
    }

    @Test
    @DisplayName("Should fail when an invalid cron expression is provided")
    public void shouldFailWhenCronExpressionIsInvalid() {
        Map<String, Object> properties = Map.of(
                "type", "interval",
                "schedule", "not-a-cron"
        );

        assertThatThrownBy(() ->
                JacksonPolymorphicUtils.convertToPolymorphicType(
                        properties,
                        TriggerConfiguration.class
                )
        ).isInstanceOf(JsonProcessingException.class);
    }
}
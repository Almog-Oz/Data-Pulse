package org.ditto.datapulse.configuration.execution;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.ditto.datapulse.models.execution.trigger.TriggerConfiguration;
import org.ditto.datapulse.utils.JacksonPolymorphicUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties("data-pulse.execution")
public class ExecutionConfiguration {
    private TriggerConfiguration trigger;

    @JsonSetter
    public void setTrigger(Map<String, Object> trigger) throws JsonProcessingException {
        this.trigger = JacksonPolymorphicUtils.convertToPolymorphicType(trigger, TriggerConfiguration.class);
    }

    @Bean
    public TriggerConfiguration triggerConfiguration() {
        return this.trigger;
    }
}

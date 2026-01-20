package org.ditto.datapulse.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.util.Map;

/**
 * Utility class for converting a generic property map into a concrete instance
 * of a polymorphic type using Jackson.
 * <p>
 * This utility is primarily intended for scenarios where configuration data
 * (e.g. parsed from YAML or JSON into a {@link Map}) must be materialized into
 * a strongly-typed object hierarchy based on Jackson polymorphic annotations
 * such as {@link com.fasterxml.jackson.annotation.JsonTypeInfo} and
 * {@link com.fasterxml.jackson.annotation.JsonSubTypes}.
 * <p>
 * The conversion is performed by serializing the input properties map into
 * JSON and then deserializing it into the provided abstract polymorphic type.
 * Jackson will resolve the concrete implementation at runtime according to
 * the polymorphic metadata defined on the target type.
 * <p>
 * /**
 * <h3>Example usage</h3>
 * <p>
 * Given a polymorphic trigger configuration hierarchy where
 * {@code TriggerConfiguration} is the abstract base type and
 * {@code OnDemandTriggerConfiguration} is one of its concrete implementations:
 * </p>
 *
 * <pre>{@code
 * Map<String, Object> properties = Map.of(
 *     "type", "ON_DEMAND",
 *     "execute-at", Map.of(
 *         "format", "yyyy-MM-dd HH:mm:ss",
 *         "time", "2026-01-20 10:30:00"
 *     )
 * );
 *
 * TriggerConfiguration triggerConfiguration =
 *     JacksonPolymorphicUtils.convertToPolymorphicType(
 *         properties,
 *         TriggerConfiguration.class
 *     );
 *
 * // Resolved at runtime as OnDemandTriggerConfiguration
 * OnDemandTriggerConfiguration onDemand =
 *     (OnDemandTriggerConfiguration) triggerConfiguration;
 *
 * LocalDateTime executionTime =
 *     onDemand.getExecuteAt().getTime();
 * }</pre>
 *
 * @author Almog Oz
 */
@UtilityClass
public class JacksonPolymorphicUtils {

    /**
     * Converts a map of properties into an instance of a polymorphic type.
     * <p>
     * The provided {@code abstractPolymorphicTypeClazz} must be annotated with
     * Jackson polymorphic configuration (e.g. {@code @JsonTypeInfo}) so that
     * Jackson can determine the correct concrete subtype during deserialization.
     *
     * @param properties                   a map representing the properties of the target object
     * @param abstractPolymorphicTypeClazz the abstract or base class of the
     *                                     polymorphic hierarchy
     * @param <T>                          the target polymorphic base type
     * @return a concrete instance resolved and deserialized by Jackson
     * @throws JsonProcessingException if serialization or deserialization fails
     */
    public static <T> T convertToPolymorphicType(
            Map<String, Object> properties,
            Class<T> abstractPolymorphicTypeClazz
    ) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String abstractJSON = objectMapper.writeValueAsString(properties);

        return objectMapper
                .readerFor(abstractPolymorphicTypeClazz)
                .readValue(abstractJSON);
    }
}

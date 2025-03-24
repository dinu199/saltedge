package com.saltedge.helpers;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ScenarioContext {

    private final Map<String, Object> objectContextData = new HashMap<>();
    private final Map<String, String> stringContextData = new ConcurrentHashMap<>();

    /**
     * Stores a value in the context.
     * @param key The key to store data under.
     * @param value The value to store.
     */
    public void set(String key, Object value) {
        objectContextData.put(key, value);
    }

    /**
     * Retrieves a value from the context.
     * @param key The key for the data.
     * @param <T> The expected type of the value.
     * @return The value or null if not found.
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> type) {
        return (T) objectContextData.get(key);
    }


    /**
     * Stores a key-value pair in the context.
     *
     * @param key The key under which the value is stored.
     */
    public void setValue(String key, String value) {
        stringContextData.put(key, value);
    }

    /**
     * Retrieves a value from the context.
     * @param key The key whose value needs to be retrieved.
     * @return The stored value, or null if not found.
     */
    public String getValue(String key) {
        return stringContextData.get(key);
    }


    /**
     * Clears the context (useful for resetting between scenarios).
     */
    public void clear() {
        objectContextData.clear();
    }
}

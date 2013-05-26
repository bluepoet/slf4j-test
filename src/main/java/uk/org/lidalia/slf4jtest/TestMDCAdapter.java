package uk.org.lidalia.slf4jtest;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Supplier;
import org.slf4j.spi.MDCAdapter;

import com.google.common.collect.ImmutableMap;

import uk.org.lidalia.lang.ThreadLocal;

public class TestMDCAdapter implements MDCAdapter {

    private final ThreadLocal<Map<String, String>> value = new ThreadLocal<>(makeEmptyMutableMap);

    private static Supplier<Map<String, String>> makeEmptyMutableMap = new Supplier<Map<String, String>>() {
        @Override
        public Map<String, String> get() {
            return new HashMap<>();
        }
    };

    public void put(final String key, final String val) {
        value.get().put(key, val);
    }

    public String get(final String key) {
        return value.get().get(key);
    }

    public void remove(final String key) {
        value.get().remove(key);
    }

    public void clear() {
        value.get().clear();
    }

    public ImmutableMap<String, String> getCopyOfContextMap() {
        return ImmutableMap.copyOf(value.get());
    }

    @SuppressWarnings("unchecked")
    public void setContextMap(final Map contextMap) {
        value.set(new HashMap<String, String>(contextMap));
    }
}
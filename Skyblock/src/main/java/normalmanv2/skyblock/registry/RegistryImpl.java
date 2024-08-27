package normalmanv2.skyblock.registry;

import normalmanv2.api.utils.registry.Registry;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class RegistryImpl<K, T> implements Registry<K, T> {

    protected final Map<K, T> backingMap;

    public RegistryImpl() {
        this.backingMap = new ConcurrentHashMap<>();
    }

    @Override
    public Map<K, T> getBackingMap() {
        return Collections.unmodifiableMap(this.backingMap);
    }

    @Override
    public void register(K key, T object) {
        Objects.requireNonNull(key, "Key Cannot be null! ");
        Objects.requireNonNull(object, "Object Cannot be null! ");
        this.backingMap.put(key, object);
    }

    @Override
    public void unregisterOrThrow(K key) {
        Objects.requireNonNull(key, "Key Cannot be null! ");
        if (!this.backingMap.containsKey(key)) {
            throw new IllegalArgumentException("Key " + key + " is not registered!");
        }
        this.backingMap.remove(key);
    }

    @Override
    public T getOrThrow(K key) {
        Objects.requireNonNull(key, "Key Cannot be null! ");
        if (!this.backingMap.containsKey(key)) {
            throw new IllegalArgumentException("Key " + key + " is not registered!");
        }
        return this.backingMap.get(key);
    }
}
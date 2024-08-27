package normalmanv2.api.utils.registry;

import java.util.Map;

public interface Registry<K, T> {
    Map<K, T> getBackingMap();
    void register(K key, T object);
    void unregisterOrThrow(K key);
    T getOrThrow(K key);
}

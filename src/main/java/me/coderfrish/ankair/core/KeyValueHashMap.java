package me.coderfrish.ankair.core;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("all")
public class KeyValueHashMap<K, V> extends HashMap<K, V> {
    private final Map<V, K> kMap = new ConcurrentHashMap<>();

    @Override
    public V put(K key, V value) {
        kMap.put(value, key);
        return super.put(key, value);
    }

    public K getKey(V value) {
        return kMap.get(value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        kMap.remove(value, key);
        return super.remove(key, value);
    }

    @Override
    public V remove(Object key) {
        kMap.remove(this.get(key));
        return super.remove(key);
    }
}

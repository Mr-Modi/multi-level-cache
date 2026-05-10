package org.example.multilevelcache.policy;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key> {

    private final Set<Key> keys;

    public LRUEvictionPolicy() {
        this.keys = new LinkedHashSet<>();
    }

    public void keyAccessed(Key key) {
        if (keys.contains(key)) {
            keys.remove(key);
        }

        keys.add(key);
    }

    public Key evictKey() {
        if (keys.isEmpty()) {
            return null;
        }
        Iterator<Key> iterator = keys.iterator();
        Key keyToRemove = iterator.next();

        iterator.remove();

        return keyToRemove;
    }
}

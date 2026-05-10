package org.example.multilevelcache.policy;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class FIFOIEvictionPolicy<Key> implements IEvictionPolicy<Key> {

    private final Set<Key> keys;

    public FIFOIEvictionPolicy() {
        this.keys = new LinkedHashSet<>();
    }

    @Override
    public void keyAccessed(Key key) {

        // In FIFO, access should NOT change ordering.
        if (!keys.contains(key)) {
            keys.add(key);
        }
    }

    @Override
    public Key evictKey() {

        if (keys.isEmpty()) {
            return null;
        }

        Iterator<Key> iterator = keys.iterator();

        Key oldestKey = iterator.next();

        iterator.remove();

        return oldestKey;
    }
}
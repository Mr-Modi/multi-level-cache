package org.example.multilevelcache.provider;

import org.example.multilevelcache.exceptions.StorageFullException;
import org.example.multilevelcache.policy.IEvictionPolicy;
import org.example.multilevelcache.storage.Storage;

public class CacheProvider<Key, Value> {

    private final IEvictionPolicy<Key> IEvictionPolicy;
    private final Storage<Key, Value> storage;

    public CacheProvider(IEvictionPolicy<Key> IEvictionPolicy, Storage<Key, Value> storage) {
        this.IEvictionPolicy = IEvictionPolicy;
        this.storage = storage;
    }

    public void set(Key key, Value value) {
        try {
            this.storage.add(key, value);
            this.IEvictionPolicy.keyAccessed(key);
        } catch (StorageFullException exception) {
            final Key keyToRemove = IEvictionPolicy.evictKey();
            if (keyToRemove == null) {
                throw new RuntimeException("Unexpected State.");
            }

            this.storage.remove(keyToRemove);
            set(key, value);
        }
    }

    public Value get(Key key) {
        final Value value = this.storage.get(key);
        if (value != null) {
            this.IEvictionPolicy.keyAccessed(key);
        }
        return value;
    }

    public double getCurrentUsage() {
        return this.storage.getCurrentUsage();
    }
}

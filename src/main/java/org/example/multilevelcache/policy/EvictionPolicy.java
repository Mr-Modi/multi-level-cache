package org.example.multilevelcache.policy;

public interface EvictionPolicy<Key> {
    void keyAccessed(Key key);
    Key evictKey();
}

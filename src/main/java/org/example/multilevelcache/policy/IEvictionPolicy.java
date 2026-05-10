package org.example.multilevelcache.policy;

public interface IEvictionPolicy<Key> {
    void keyAccessed(Key key);
    Key evictKey();
}

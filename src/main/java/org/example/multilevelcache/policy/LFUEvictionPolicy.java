package org.example.multilevelcache.policy;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUEvictionPolicy<Key> implements EvictionPolicy<Key> {

    private final Map<Key, Integer> keyFrequencyMap;

    private final Map<Integer, LinkedHashSet<Key>> frequencyKeysMap;

    private int minFrequency;

    public LFUEvictionPolicy() {

        this.keyFrequencyMap = new HashMap<>();
        this.frequencyKeysMap = new HashMap<>();
        this.minFrequency = 0;
    }

    @Override
    public void keyAccessed(Key key) {

        if (keyFrequencyMap.containsKey(key)) {

            int currentFrequency = keyFrequencyMap.get(key);

            frequencyKeysMap.get(currentFrequency).remove(key);

            // Cleanup empty frequency bucket
            if (frequencyKeysMap.get(currentFrequency).isEmpty()) {

                frequencyKeysMap.remove(currentFrequency);

                if (minFrequency == currentFrequency) {
                    minFrequency++;
                }
            }

            int newFrequency = currentFrequency + 1;

            keyFrequencyMap.put(key, newFrequency);

            frequencyKeysMap
                    .computeIfAbsent(newFrequency, k -> new LinkedHashSet<>())
                    .add(key);

        } else {

            keyFrequencyMap.put(key, 1);

            frequencyKeysMap
                    .computeIfAbsent(1, k -> new LinkedHashSet<>())
                    .add(key);

            minFrequency = 1;
        }
    }

    @Override
    public Key evictKey() {

        if (frequencyKeysMap.isEmpty()) {
            return null;
        }

        LinkedHashSet<Key> keys = frequencyKeysMap.get(minFrequency);

        Key evictKey = keys.iterator().next();

        keys.remove(evictKey);

        if (keys.isEmpty()) {
            frequencyKeysMap.remove(minFrequency);
        }

        keyFrequencyMap.remove(evictKey);

        return evictKey;
    }
}
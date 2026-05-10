package org.example.multilevelcache.provider;

import org.example.multilevelcache.model.ReadResponse;
import org.example.multilevelcache.model.WriteResponse;

import java.util.Collections;
import java.util.List;

public class NullEffectLevelCache<Key, Value> implements ILevelCache<Key, Value> {
    @Override
    public WriteResponse set(Key key, Value value) {
        return new WriteResponse(0.0);
    }

    @Override
    public ReadResponse<Value> get(Key key) {
        return new ReadResponse<>(null, 0.0);
    }

    @Override
    public List<Double> getUsages() {
        return Collections.emptyList();
    }
}

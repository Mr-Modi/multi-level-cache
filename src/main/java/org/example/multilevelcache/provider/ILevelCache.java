package org.example.multilevelcache.provider;

import lombok.NonNull;
import org.example.multilevelcache.model.ReadResponse;
import org.example.multilevelcache.model.WriteResponse;

import java.util.List;

public interface ILevelCache<Key, Value> {

    @NonNull
    WriteResponse set(Key key, Value value);

    @NonNull
    ReadResponse<Value> get(Key key);

    @NonNull
    List<Double> getUsages();
}

package org.example.multilevelcache.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LevelCacheData {
    int readTime;
    int writeTime;
}

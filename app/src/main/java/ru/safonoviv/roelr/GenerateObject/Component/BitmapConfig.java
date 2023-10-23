package ru.safonoviv.roelr.GenerateObject.Component;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class BitmapConfig {
    private final Set<String> keys;
    private final String key;

    public BitmapConfig(String key) {
        this.key = key;
        keys = new HashSet<>();
    }

    public void addKey(String key) {
        keys.add(key);
    }
}

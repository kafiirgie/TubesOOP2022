package com.monstersaku.util;

import java.util.List;
import java.util.ArrayList;

public class Map<K, V> {
    private List<KeyValue<K, V>> contents;
    private int size;

    private static class KeyValue<K, V> {
        private K key;
        private V value;

        public KeyValue(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public Map() {
        this.contents = new ArrayList<KeyValue<K, V>>();
        this.size = 0;
    }

    public boolean containsKey(K key) {
        boolean retValue = false;
        for (KeyValue<K,V> content : contents) {
            if (content.getKey().equals(key)) {
                retValue = true;
            }
        }
        return retValue;
    }

    public void put(K key, V value) {
        if (!containsKey(key)) {
            KeyValue<K, V> newContent = new KeyValue<K, V>(key, value);
            contents.add(newContent);
            this.size += 1;
        } else {
            for (KeyValue<K,V> content : contents) {
                if (content.getKey().equals(key)) {
                    content.setValue(value);
                }
            }
        }
    }

    public V get(K key) {
        if (containsKey(key)) {
            KeyValue<K, V> foundContent = new KeyValue<K, V>(null, null);
            for (KeyValue<K, V> content : contents) {
                if (content.getKey().equals(key)) {
                    foundContent = content;
                    break;
                }
            }
            return foundContent.getValue();
        } else {
            return null;
        }
    }

    public int size() {
        return this.size;
    }
    
}

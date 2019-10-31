package com.demo.commons;

/**
 * 键值对
 *
 * @param <K>
 * @param <V>
 */
public class Pair<K, V> {

    private K k;
    private V v;

    public Pair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K getK() {
        return k;
    }

    public void setK(K k) {
        this.k = k;
    }
}

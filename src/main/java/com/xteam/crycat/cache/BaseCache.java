package com.xteam.crycat.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseCache<K, V> {
    private Map<K,V> cache;

    private BaseCache() {
        if(cache == null){
            cache = new ConcurrentHashMap<>();
        }
    }

    /**
     * 从缓存获取数据
     * @author coshaho
     * @param param
     * @return
     */
    public V get(K param) {
        return cache.get(param);
    }

    /**
     * 主动设置缓存数据
     * @author coshaho
     * @param k
     * @param v
     */
    public void putIfAbsent(K k, V v) {
        cache.putIfAbsent(k, v);
    }

    public void put(K k, V v) {
        cache.put(k, v);
    }
}

package cn.linguolai.template.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ShiroRedisCacheManager implements CacheManager {


    //用于存放Cache
    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap();

    @Resource
    private ShiroRedisCache cache;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        Cache c = caches.get(name);

        if (c == null) {
            c = cache;
            //添加到Map中
            caches.put(name, c);
        }
        return c;
    }
}

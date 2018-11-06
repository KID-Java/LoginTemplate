package cn.linguolai.template.cache;

import cn.linguolai.template.utils.jedis.JedisUtlis;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.util.*;
@Component
public class ShiroRedisCache<K,V> implements Cache<K,V> {


    //前缀
    private static final String CACHE_PERFIX = ShiroRedisCache.class.getName() + ":";


    @Resource()
    private JedisUtlis jedisUtlis;

    /**
     * 格式化前缀
     * @param key
     * @return
     */
    private byte[] formatKey(K key) {
        if (key instanceof String) {
            return (CACHE_PERFIX + key).getBytes();
        }
        return SerializationUtils.serialize(key);
    }


    @Override
    public V get(K k) throws CacheException {
        if (k == null) {
            return null;
        }
        byte[] value = jedisUtlis.get(formatKey(k));
        return (V) SerializationUtils.deserialize(value);
    }

    @Override
    public V put(K k, V v) throws CacheException {
        if (k == null || v == null) {
            return null;
        }

        //设置过期时间为600S
        jedisUtlis.set(formatKey(k), SerializationUtils.serialize(v),600);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        if (k == null) {
            return null;
        }
        byte[] value = jedisUtlis.del(formatKey(k));
        return (V) SerializationUtils.deserialize(value);
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        Long longSize = new Long(jedisUtlis.dbSize());
        return longSize.intValue();
    }

    @Override
    public Set<K> keys() {
        Set<byte[]> keys = jedisUtlis.keys(CACHE_PERFIX);
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.emptySet();
        }else{
            Set<K> newKeys = new HashSet<K>();
            for(byte[] key:keys){
                newKeys.add((K)key);
            }
            return newKeys;
        }
    }

    @Override
    public Collection<V> values() {
        Set<byte[]> keys = jedisUtlis.keys(CACHE_PERFIX);
        if (!CollectionUtils.isEmpty(keys)) {
            List<V> values = new ArrayList<V>(keys.size());
            for (byte[] key : keys) {
                V value = get((K)key);
                if (value != null) {
                    values.add(value);
                }
            }
            return Collections.unmodifiableList(values);
        } else {
            return Collections.emptyList();
        }
    }
}

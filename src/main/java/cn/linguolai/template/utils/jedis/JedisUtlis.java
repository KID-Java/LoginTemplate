package cn.linguolai.template.utils.jedis;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Jedis工具类
 */
@Component
public class JedisUtlis {

    private static final Logger log = Logger.getLogger(RedisUtils.class);

    @Resource(name = "jedisPool")
    private JedisPool jedisPool;

    /**
     * 获取Jedis
     * @return
     */
    private Jedis getJedis() {

        Jedis jedis = null;
        try {
            if (jedisPool != null) {
                jedis = jedisPool.getResource();
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }

        return jedis;
    }


    /**
     * 设置值
     * @param key
     * @param value
     * @return
     */
    public String set(byte[] key, byte[] value) {
        Jedis jedis = getJedis();
        try {
            return jedis.set(key, value);
        } finally {
            jedis.close();
        }
    }

    /**
     * 设置值同时设置存活时间
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public String set(byte[] key, byte[] value,int expire) {
        Jedis jedis = getJedis();
        try {
            if (expire != 0) {
                jedis.expire(key, expire);
            }
            return jedis.set(key, value);
        } finally {
            jedis.close();
        }
    }


    /**
     * 获取值
     * @param key
     * @return
     */
    public byte[] get(byte[] key){
        byte[] value;
        Jedis jedis = getJedis();
        try{
            value = jedis.get(key);
        }finally{
            jedis.close();
        }
        return value;
    }



    /**
     * del
     * @param key
     */
    public byte[] del(byte[] key){
        Jedis jedis = getJedis();
        try{
            byte[] value = jedis.get(key);
            jedis.del(key);
            return value;
        }finally{
            jedis.close();
        }
    }

    /**
     * flush
     */
    public void flushDB(){
        Jedis jedis = getJedis();
        try{
            jedis.flushDB();
        }finally{
            jedis.close();
        }
    }

    /**
     * size
     */
    public Long dbSize(){
        Long dbSize;
        Jedis jedis = getJedis();
        try{
            dbSize = jedis.dbSize();
        }finally{
            jedis.close();
        }
        return dbSize;
    }

    /**
     * keys
     * @param pattern
     * @return
     */
    public Set<byte[]> keys(String pattern){
        Set<byte[]> keys;
        Jedis jedis = getJedis();
        try{
            keys = jedis.keys((pattern + "*").getBytes());
        }finally{
            jedis.close();
        }
        return keys;
    }


}

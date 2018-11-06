package cn.linguolai.template.session;

import cn.linguolai.template.cache.ShiroRedisCache;
import cn.linguolai.template.utils.jedis.JedisUtlis;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ShiroRedisSessionDao extends AbstractSessionDAO {


    //前缀
    private final String SHIRO_SESSION_PREFIX = ShiroRedisSessionDao.class.getName() + ":";

    @Resource
    private JedisUtlis jedisUtlis;
    /**
     * 格式化key
     * @param key
     * @return
     */
    private byte[] formatKey(String key) {
        return (SHIRO_SESSION_PREFIX + key).getBytes();
    }

    @Override
    protected Serializable doCreate(Session session) {
        if (session == null) {
            return null;
        }
        Serializable sessionId = generateSessionId(session);
        //捆绑sessionId 和 session
        assignSessionId(session, sessionId);
        saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        System.out.println("读取Session...");

        if (serializable == null) {
            return null;
        }
        byte[] value = jedisUtlis.get(formatKey(serializable.toString()));

        return (Session) SerializationUtils.deserialize(value);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if (session == null || session.getId() == null) {
            return;
        }
        saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            return;
        }
        jedisUtlis.del(formatKey(session.getId().toString()));

    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<byte[]> keys = jedisUtlis.keys(SHIRO_SESSION_PREFIX);
        Set<Session> sessions =new HashSet();
        if(CollectionUtils.isEmpty(keys)){
            return sessions;
        }
        for(byte[] key:keys){
            Session session =(Session)SerializationUtils.deserialize(jedisUtlis.get(key));
            sessions.add(session);
        }

        return sessions;
    }

    private void saveSession(Session session){
        if(session !=null && session.getId() !=null){
            byte[] key = formatKey(session.getId().toString());
            byte[] value = SerializationUtils.serialize(session);
            jedisUtlis.set(key,value,600);
        }
    }
}

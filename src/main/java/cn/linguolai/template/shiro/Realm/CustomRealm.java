package cn.linguolai.template.shiro.Realm;

import cn.linguolai.template.bean.User;
import cn.linguolai.template.mapper.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    @Resource
    private UserMapper userMapper;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        if (principalCollection.isEmpty()) {
            return null;
        }

        //获取用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        //根据用户名查询角色
        Set<String> roles = userMapper.selectRolesByUsername(username);
        //根据用户名查询权限
        Set<String> permissions = userMapper.selectPermissionsByUsername(username);

        //创建授权信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //设置角色和权限
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);

        return authorizationInfo;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        if (authenticationToken == null) {
            return null;
        }

        //获取用户名
        String username = (String) authenticationToken.getPrincipal();


        //获取密码
        User user = userMapper.selectByPrimaryKey(username);
        if (user == null || user.getPassword() == null) {
            return null;
        }
        String password = user.getPassword();

        //创建认证信息
        SimpleAuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(username, password, ByteSource.Util.bytes(user.getSalt()), "CustomRealm");

        //设置盐
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
        return authenticationInfo;
    }
}

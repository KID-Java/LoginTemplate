import java.io.Serializable;

/**
 * 用户_角色
 *
 * @author itar
 * @mail wuhandzy@gmail.com
 * @date 2018-09-17 06:13:38
 * @since jdk1.8
 */
public class TbUsersRolesPo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;
    /**
     * 角色
     */
    private String role;

    /**
     * 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 角色
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * 角色
     */
    public String getRole() {
        return role;
    }
}

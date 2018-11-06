package cn.linguolai.template.mapper;

import cn.linguolai.template.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserMapper {


    int deleteByPrimaryKey(String username);

    int insert(User record);

    User selectByPrimaryKey(String username);

    List<User> selectAll();

    int updateByPrimaryKey(User record);


    Set<String> selectPermissionsByUsername(@Param("username") String username);

    Set<String> selectRolesByUsername(@Param("username")String username);
}
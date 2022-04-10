package com.ydlclass.mapper;

import com.ydlclass.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 小松
 */
public interface UserMapper {

    /**
     * @return
     */
    List<User> selectAll();


    User selectOne (int id);

    List<User> select(@Param("username") String username, @Param("password")String password);

    List<User> selectByUser(User user);

    /**
     * 新增用户
     */
    int insert(User user);

    /**
     * 修改用户
     */
    int update(User user);

    /**
     * 删除一些用户
     * @param ids
     */

    int deleteByIds(@Param("ids") List<Integer>ids);

    /**
     * 批量添加
     * @param users
     * @return
     */
    int insertByIds(@Param("users") List<User> users);


}

package com.hrd.springsecurity.repository;

import com.hrd.springsecurity.repository.model.Role;
import com.hrd.springsecurity.repository.model.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsRepo {
    @Select("SELECT * FROM tb_users where username = #{username}")
    @Results({
            @Result(property = "name", column = "username"),
            @Result(property = "id", column = "id"),
            @Result(property = "roles", column = "id", many = @Many(select = "findRolesByUserId"))
    })
    User loadUserByUsername(String username);

    @Select("SELECT * FROM tb_roles r INNER JOIN tb_user_roles u on r.id = u.role_id WHERE u.user_id = #{id}")
    List<Role> findRolesByUserId(int id);
}

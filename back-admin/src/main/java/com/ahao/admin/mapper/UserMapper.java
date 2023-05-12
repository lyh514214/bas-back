package com.ahao.admin.mapper;

import com.ahao.admin.param.user.UserPageAndSearch;
import com.ahao.admin.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: UserMapper
 * @Author: ahao
 * @Date: 2023/4/13 22:52
 **/

public interface UserMapper extends BaseMapper<User> {

    List<User> likeGetListByPage(@Param("userPageAndSearch") UserPageAndSearch userPageAndSearch);

    Integer likeGetListByPageLimit (@Param("userPageAndSearch") UserPageAndSearch userPageAndSearch);


}

package com.ahao.admin.service;

import com.ahao.admin.param.login.LoginParam;
import com.ahao.admin.param.login.RegisterParam;
import com.ahao.admin.param.user.UserPageAndSearch;
import com.ahao.admin.pojo.User;
import com.ahao.admin.utils.R;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: UserService
 * @Author: ahao
 * @Date: 2023/4/13 22:57
 **/

public interface UserService {

    R getOne(Integer id);

    R likeGetListByPage(UserPageAndSearch userPageAndSearch);

    R updateStatus(Integer userId , Boolean status ,Integer updateBy);

    R saveUserInfo(User user);

    R removeUserInfo(Integer userId);

    R removeUserList(List<User> userList);

    R updateUserInfo(User user);

    R toLogin(LoginParam loginParam);

    R register(RegisterParam registerParam);


}

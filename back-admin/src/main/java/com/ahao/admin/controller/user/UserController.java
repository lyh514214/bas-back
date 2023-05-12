package com.ahao.admin.controller.user;

import com.ahao.admin.param.user.UserPageAndSearch;
import com.ahao.admin.pojo.User;
import com.ahao.admin.service.UserService;
import com.ahao.admin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Description: UserController
 * @Author: ahao
 * @Date: 2023/4/13 22:53
 **/

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Description: 获取一个用户信息
     * @param id
     * @return com.ahao.admin.utils.R
    **/
    @GetMapping("getOne")
    public R getOne(@RequestParam("user_id") Integer id){
        return userService.getOne(id);
    }

    /**
     * @Description: 分页+模糊查询
     * @param userPageAndSearch 当前页、页容量、昵称、账号
     * @return com.ahao.admin.utils.R
     **/
    @PostMapping("likeGetListByPage")
    public R likeGetListByPage(@RequestBody UserPageAndSearch userPageAndSearch){
        return userService.likeGetListByPage(userPageAndSearch);
    }

    /**
     * @Description: 更新账号状态
     * @param userId 用户id
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("updateStatus")
    public R updateStatus(@RequestParam("user_id") Integer userId, @RequestParam("userStatus") Boolean status,@RequestParam("update_by") Integer updateBy){
        return userService.updateStatus(userId,status,updateBy);
    }

    /**
     * @Description: 保存用户信息
     * @param user  用户信息
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("saveUserInfo")
    public R saveUserInfo(@RequestBody User user){
        return userService.saveUserInfo(user);
    }

    /**
     * @Description: 删除用户信息
     * @param userId  用户id
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("removeUserInfo")
    public R removeUserInfo(@RequestParam("user_id") Integer userId){
        return userService.removeUserInfo(userId);
    }

    /**
     * @Description: 批量删除 用户信息
     * @param userList  用户集合
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("removeUserList")
    public R removeUserList(@RequestBody List<User> userList){
        System.out.println(userList);
        return userService.removeUserList(userList);
    }

    /**
     * @Description: 修改用户信息
     * @param user 用户信息参数
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("updateUserInfo")
    public R updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

}

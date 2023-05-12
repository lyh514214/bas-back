package com.ahao.admin.controller;
import com.ahao.admin.param.user.UserPageAndSearch;
import com.ahao.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Description: 测试
 * @Author: ahao
 * @Date: 2023/4/13 21:56
 **/

@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public void test(@RequestBody UserPageAndSearch userPageAndSearch) {
    }

}

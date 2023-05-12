package com.ahao.admin.controller.login;

import cn.hutool.captcha.*;
import com.ahao.admin.param.login.LoginParam;
import com.ahao.admin.param.login.RegisterParam;
import com.ahao.admin.service.UserService;
import com.ahao.admin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @Description: 登录控制器
 * @Author: ahao
 * @Date: 2023/4/25 23:37
 **/

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * @Description: 设置和保存验证码
     * @param request
     * @param response
     * @param session
     * @return void
    **/
    @GetMapping("captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response , HttpSession session) throws IOException {
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(100, 30, 4, 10);
        String code = captcha.getCode();
        session.setAttribute("captcha",code);
        captcha.write(response.getOutputStream());
    }

    /**
     * @Description: 登录
     * @param loginParam  账号、密码、验证码
     * @param session  验证码（答案)
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("toLogin")
    public R toLogin(@RequestBody LoginParam loginParam,HttpSession session) {
        String code = loginParam.getCode();
        String captcha = (String) session.getAttribute("captcha");
        if (!code.equalsIgnoreCase(captcha)){
            return R.ERR_CAPTCHA();
        }
        return userService.toLogin(loginParam);
    }

    /**
     * @Description: 注册
     * @param registerParam 账号、密码
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("register")
    public R register(@RequestBody RegisterParam registerParam){
        return userService.register(registerParam);
    }




}

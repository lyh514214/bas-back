package com.ahao.admin.param.login;

import lombok.Data;

/**
 * @Description: LoginParam
 * @Author: ahao
 * @Date: 2023/4/26 0:28
 **/

@Data
public class LoginParam {

    private String username;
    private String password;
    private String code;

}

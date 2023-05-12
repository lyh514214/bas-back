package com.ahao.admin.param.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 注册参数类
 * @Author: ahao
 * @Date: 2023/4/26 17:19
 **/

@Data
public class RegisterParam implements Serializable {
    public static final long SerialVersionUID = 1L;

    private String username;
    private String password;
    @JsonProperty("create_time")
    private Date createTime;

}

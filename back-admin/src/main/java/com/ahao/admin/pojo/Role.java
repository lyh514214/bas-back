package com.ahao.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 角色实体类
 * @Author: ahao
 * @Date: 2023/4/28 21:55
 **/

@Data
@TableName("user_role")
public class Role implements Serializable {
    public static final long SerialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("role_id")
    private Integer roleId;   // 角色id
    @JsonProperty("role_name")
    private String roleName;   // 角色名称

}

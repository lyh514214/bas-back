package com.ahao.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @Description: 用户类
 * @Author: ahao
 * @Date: 2023/4/13 22:14
 **/

@Data
@TableName("user")
public class User implements Serializable {

    public static final long SerialVersionUID = 1L;

    @JsonProperty("user_id")
    @TableId(type = IdType.AUTO)
    private Integer userId;      //用户id
    @JsonProperty("dept_id")
    private Integer deptId;      //部门id
    @JsonProperty("role_id")
    private Integer roleId;      //角色id
    @JsonProperty("user_name")
    private String userName;     //用户名
    @JsonProperty("nick_name")
    private String nickName;     //昵称
    @JsonProperty("user_address")
    private String userAddress;    //地址
    private String email;        //邮箱
    @JsonProperty("phone_number")
    private String phoneNumber;    //电话
    private Integer sex;           //性别
    private String avatar;        //头像地址
    private String password;      //密码
    private Boolean status;        //账号状态
    @JsonProperty("create_by")
    private Integer createBy;      //创建者id
    @JsonProperty("create_time")
    private Date createTime;     //创建时间
    @JsonProperty("update_by")
    private Integer updateBy;      //更新者id
    @JsonProperty("update_time")
    private Date updateTime;      //更新时间
    private String remark;        //备注

    //转换参数
    @TableField(exist = false)
    @JsonProperty("dept_name")
    private String deptName;   //部门

    @TableField(exist = false)
    @JsonProperty("role_name")
    private String roleName;   //部门

    @TableField(exist = false)
    @JsonProperty("create_by_name")
    private String createByName;   //创建者

    @TableField(exist = false)
    @JsonProperty("update_by_name")
    private String updateByName;   //更新者



    // status状态表示 转变为布尔类型
    public void setStatus(String status) {
        if (Objects.equals(status, "0")){
            this.status = false;
        } else {
            this.status = true;
        }
    }

//    //sex 转变为中文
//    public void setSex(String sex) {
//        if (Objects.equals(sex,"0")){
//            this.sex = "女";
//        } else {
//            this.sex = "男";
//        }
//    }

}

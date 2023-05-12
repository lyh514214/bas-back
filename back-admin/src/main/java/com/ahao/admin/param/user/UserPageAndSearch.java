package com.ahao.admin.param.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 分页+模糊查询 入参实体类
 * @Author: ahao
 * @Date: 2023/4/22 12:38
 **/

@Data
public class UserPageAndSearch implements Serializable {

    public static final long SerialVersionUID = 1L;

    private Integer currentPage;
    private Integer pageSize;
    private String nickName;
    private String userName;

}

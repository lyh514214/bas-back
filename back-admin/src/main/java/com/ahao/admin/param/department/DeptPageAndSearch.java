package com.ahao.admin.param.department;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 分页+模糊查询 入参实体类
 * @Author: ahao
 * @Date: 2023/4/22 17:29
 **/

@Data
public class DeptPageAndSearch implements Serializable {

    public static final long SerialVersionUID = 1L;

    private Integer currentPage;
    private Integer pageSize;
    private String deptName;


}

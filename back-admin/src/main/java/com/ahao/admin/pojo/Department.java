package com.ahao.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 部门实体类
 * @Author: ahao
 * @Date: 2023/4/22 11:43
 **/

@Data
@TableName("user_department")
public class Department implements Serializable {

    public static final long SerialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("dept_id")
    private Integer deptId;
    @JsonProperty("dept_name")
    private String deptName;


}

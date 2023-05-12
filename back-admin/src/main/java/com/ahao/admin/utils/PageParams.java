package com.ahao.admin.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 分页参数
 * @Author: ahao
 * @Date: 2023/4/22 11:08
 **/

@Data
public class PageParams implements Serializable {
    public static final long SerialVersionUID = 1L;

    private Integer currentPage;
    private Integer pageSize;

}

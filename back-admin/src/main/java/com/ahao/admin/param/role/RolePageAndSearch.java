package com.ahao.admin.param.role;

import com.ahao.admin.utils.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description: RolePageAndSearch
 * @Author: ahao
 * @Date: 2023/4/29 0:01
 **/

@Data
@EqualsAndHashCode
public class RolePageAndSearch extends PageParams implements Serializable {
    public static final long SerialVersionUID = 1L;
}

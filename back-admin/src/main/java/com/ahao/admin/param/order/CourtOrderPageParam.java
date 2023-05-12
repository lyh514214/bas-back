package com.ahao.admin.param.order;

import com.ahao.admin.utils.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description: CourtOrderPageParam
 * @Author: ahao
 * @Date: 2023/5/9 16:08
 **/

@Data
@EqualsAndHashCode(callSuper = true)
public class CourtOrderPageParam extends PageParams implements Serializable {
    public static final long SerialVersionUID = 1L;

    public Integer currentUserId;
    public Integer currentUserRoleId;


}

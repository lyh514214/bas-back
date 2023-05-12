package com.ahao.admin.param.order;

import com.ahao.admin.utils.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description: EquipmentRentOrderParam
 * @Author: ahao
 * @Date: 2023/5/11 17:11
 **/

@Data
@EqualsAndHashCode
public class EquipmentRentOrderParam extends PageParams implements Serializable {
    public static final long SerialVersionUID = 1L;

    private Integer currentUserId;
    private Integer currentUserRoleId;

}

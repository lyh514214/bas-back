package com.ahao.admin.param.order;

import com.ahao.admin.utils.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description: EquipmentBuyOrderParam
 * @Author: ahao
 * @Date: 2023/5/11 18:22
 **/

@Data
@EqualsAndHashCode(callSuper = true)
public class EquipmentBuyOrderParam extends PageParams implements Serializable {
    public static final long SerialVersionUID = 1L;

    private Integer currentUserId;
    private Integer currentUserRoleId;


}

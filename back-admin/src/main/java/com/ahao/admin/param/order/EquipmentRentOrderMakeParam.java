package com.ahao.admin.param.order;

import com.ahao.admin.pojo.EquipmentRentOrder;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description: 器材租赁订单保存
 * @Author: ahao
 * @Date: 2023/5/11 14:28
 **/

@Data
public class EquipmentRentOrderMakeParam implements Serializable {
    public static final long SerialVersionUID = 1L;

    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("create_time")
    private Date createTime;

    @JsonProperty("equipment_array")
    private List<EquipmentRentOrder> equipmentArray;



}

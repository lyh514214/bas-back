package com.ahao.admin.pojo;

import com.ahao.admin.utils.R;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: EquipmentRentOrder
 * @Author: ahao
 * @Date: 2023/5/11 0:00
 **/

@Data
@TableName("equipment_rent_order")
public class EquipmentRentOrder implements Serializable {
    public static final long SerialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("order_id")
    private Integer orderId;
    @JsonProperty("snow_id")
    private String snowId;  //用来区分同时多商品相同id
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("equipment_id")
    private Integer equipmentId;
    @JsonProperty("equipment_name")
    private String equipmentName;
    private Double rent;
    @JsonProperty("rent_number")
    private Integer number;
    @JsonProperty("total_price")
    private Double totalPrice;
    @JsonProperty("create_time")
    private Date createTime;
    private Character returned;
    @JsonProperty("return_time")
    private Date returnTime;



}

package com.ahao.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 器材购买订单
 * @Author: ahao
 * @Date: 2023/5/11 16:10
 **/

@Data
@TableName("equipment_buy_order")
public class EquipmentBuyOrder implements Serializable {
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
    private Double price;
    @JsonProperty("buy_number")
    private Integer number;
    @JsonProperty("total_price")
    private Double totalPrice;
    @JsonProperty("create_time")
    private Date createTime;

}

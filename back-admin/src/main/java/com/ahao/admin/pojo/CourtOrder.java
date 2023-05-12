package com.ahao.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: CourtOrder
 * @Author: ahao
 * @Date: 2023/5/9 15:10
 **/

@Data
@TableName("court_order")
public class CourtOrder implements Serializable {
    public static final long SerialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("court_order_id")
    private Integer courtOrderId;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("court_id")
    private Integer courtId;
    private Double rent;
    private String returned;
    @JsonProperty("create_time")
    private Date createTime;
    @JsonProperty("return_time")
    private Date returnTime;

    @TableField(exist = false)
    @JsonProperty("user_name")
    private String userName;

}

package com.ahao.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 器材
 * @Author: ahao
 * @Date: 2023/5/7 16:30
 **/

@Data
@TableName("equipment")
public class Equipment implements Serializable {
    public static final long SerialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("equipment_id")
    private Integer equipmentId;
    @JsonProperty("equipment_name")
    private String equipmentName;
    @JsonProperty("equipment_img")
    private String equipmentImg;
    private String description;
    private Integer inventory;
    private Double price;
    private Double rent;


}

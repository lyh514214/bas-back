package com.ahao.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 球场
 * @Author: ahao
 * @Date: 2023/5/3 16:17
 **/

@Data
@TableName("court")
public class Court implements Serializable {
    public static final long SerialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("court_id")
    private Integer courtId;
    @JsonProperty("court_name")
    private String courtName;
    private Double rent;
    @JsonProperty("court_region_id")
    private Integer courtRegionId;
    @JsonProperty("court_category_id")
    private Integer courtCategoryId;
    @JsonProperty("court_status")
    private String courtStatus;

    @JsonProperty("court_region")
    @TableField(exist = false)
    private String courtRegion;
    @JsonProperty("court_category")
    @TableField(exist = false)
    private String courtCategory;

}

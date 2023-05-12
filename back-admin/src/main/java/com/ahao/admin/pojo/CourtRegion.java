package com.ahao.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: CourtRegion
 * @Author: ahao
 * @Date: 2023/5/3 16:39
 **/

@Data
@TableName("court_region")
public class CourtRegion implements Serializable {
    public static final long SerialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("court_region_id")
    private Integer courtRegionId;
    @JsonProperty("court_region_name")
    private String courtRegionName;
    @JsonProperty("court_region_img")
    private String courtRegionImg;
    private String description;
}

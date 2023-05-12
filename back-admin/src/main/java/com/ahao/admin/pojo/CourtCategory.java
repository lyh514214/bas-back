package com.ahao.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: CourtCategory
 * @Author: ahao
 * @Date: 2023/5/3 16:43
 **/

@Data
@TableName("court_category")
public class CourtCategory implements Serializable {
    public static final long SerialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("court_category_id")
    private Integer courtCategoryId;
    @JsonProperty("court_category_name")
    private String courtCategoryName;
}

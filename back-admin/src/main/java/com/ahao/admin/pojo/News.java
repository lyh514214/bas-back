package com.ahao.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 轮播图
 * @Author: ahao
 * @Date: 2023/5/8 18:41
 **/

@Data
@TableName("news")
public class News implements Serializable {
    public static final long SerialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("news_id")
    private Integer newsId;
    private String title;
    @JsonProperty("img_path")
    private String imgPath;
    private String description;
}

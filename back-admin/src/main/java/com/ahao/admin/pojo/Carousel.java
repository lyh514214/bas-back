package com.ahao.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Description: 轮播图
 * @Author: ahao
 * @Date: 2023/5/8 18:41
 **/

@Data
@TableName("carousel")
public class Carousel {

    @TableId(type = IdType.AUTO)
    @JsonProperty("carousel_id")
    private Integer carouselId;
    @JsonProperty("img_path")
    private String imgPath;
    private String describes;
    @JsonProperty("news_id")
    private Integer newsId;

}

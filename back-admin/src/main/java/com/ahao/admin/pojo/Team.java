package com.ahao.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Description: Team
 * @Author: ahao
 * @Date: 2023/5/5 16:20
 **/

@Data
@TableName("team")
public class Team implements Serializable {
    public static final long SerialVersionUID = 1L;

    @JsonProperty("team_id")
    @TableId(type = IdType.AUTO)
    private Integer teamId;
    @JsonProperty("team_name")
    private String teamName;
    @JsonProperty("create_time")
    private Date createTime;
    @JsonProperty("update_time")
    private Date updateTime;

}

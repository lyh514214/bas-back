package com.ahao.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Description: 球员
 * @Author: ahao
 * @Date: 2023/5/5 18:22
 **/


@Data
@TableName("player")
public class Player implements Serializable {
    public static final long SerialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("player_id")
    private Integer playerId;
    @JsonProperty("player_name")
    private String playerName;
    private String telephone;
    private Integer age;
    private Character sex;
    private Integer height;
    private Integer weight;
    @JsonProperty("player_role")
    private String playerRole;
    @JsonProperty("team_id")
    private Integer teamId;
    @JsonProperty("create_time")
    private Date createTime;
    @JsonProperty("update_time")
    private Date updateTime;

    @TableField(exist = false)
    @JsonProperty("team_name")
    private String teamName;

}

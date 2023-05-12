package com.ahao.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Description: 比赛
 * @Author: ahao
 * @Date: 2023/5/4 22:43
 **/

@TableName("game")
@Data
public class Game implements Serializable {
    public static final long SerialVersion = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("game_id")
    private Integer gameId;

    @JsonProperty("game_name")
    private String gameName;
    @JsonProperty("A_team_name")
    private String ATeamName;
    @JsonProperty("B_team_name")
    private String BTeamName;
    @JsonProperty("score")
    private String score;
    @JsonProperty("court_name")
    private String courtName;
    @JsonProperty("remark")
    private String remark;
    @JsonProperty("create_time")
    private Date createTime;


}

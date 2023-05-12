package com.ahao.admin.param.game;

import com.ahao.admin.utils.PageParams;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description: PlayerPageSearch
 * @Author: ahao
 * @Date: 2023/5/5 22:38
 **/

@Data
@EqualsAndHashCode(callSuper = true)
public class PlayerPageSearch extends PageParams implements Serializable {
    public static final long SerialVersionUID = 1L;

    @JsonProperty("player_name")
    private String playerName;
    @JsonProperty("team_id")
    private Integer teamId;
}

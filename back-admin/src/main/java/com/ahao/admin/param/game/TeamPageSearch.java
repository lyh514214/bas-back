package com.ahao.admin.param.game;

import com.ahao.admin.utils.PageParams;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description: TeamPageSearch
 * @Author: ahao
 * @Date: 2023/5/5 19:06
 **/

@Data
@EqualsAndHashCode(callSuper = true)
public class TeamPageSearch extends PageParams implements Serializable {

    @JsonProperty("team_name")
    private String teamName;

}

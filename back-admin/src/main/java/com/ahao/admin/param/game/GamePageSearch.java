package com.ahao.admin.param.game;

import com.ahao.admin.utils.PageParams;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.sql.Date;

/**
 * @Description: GamePageSearch
 * @Author: ahao
 * @Date: 2023/5/4 22:55
 **/

@Data
@EqualsAndHashCode(callSuper = true)
public class GamePageSearch extends PageParams implements Serializable {
    public static final long SerialVersionUID = 1L;

    @JsonProperty("game_name")
    private String gameName;
    @JsonProperty("create_time")
    private Date createTime;

}

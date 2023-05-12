package com.ahao.admin.service;

import com.ahao.admin.param.game.TeamPageSearch;
import com.ahao.admin.pojo.Team;
import com.ahao.admin.utils.R;

/**
 * @Description: TeamService
 * @Author: ahao
 * @Date: 2023/5/5 16:18
 **/

public interface TeamService {

    R getList();

    R likeGetListByPage(TeamPageSearch pageSearch);

    R saveInfo(Team team);

    R updateInfo(Team team);

    R removeInfo(Integer teamId);
}

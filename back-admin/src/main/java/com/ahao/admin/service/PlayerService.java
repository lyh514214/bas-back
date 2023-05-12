package com.ahao.admin.service;

import com.ahao.admin.param.game.PlayerPageSearch;
import com.ahao.admin.pojo.Player;
import com.ahao.admin.utils.R;

import java.util.List;

/**
 * @Description: PlayerService
 * @Author: ahao
 * @Date: 2023/5/5 18:32
 **/

public interface PlayerService {

    R getList();

    R likeGetListByPage(PlayerPageSearch pageSearch);

    R saveInfo(Player player);

    R removeInfo(Integer id);

    R removeList(List<Player> playerList);

    R updateInfo(Player player);

}

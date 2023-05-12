package com.ahao.admin.service;

import com.ahao.admin.param.game.GamePageSearch;
import com.ahao.admin.pojo.Game;
import com.ahao.admin.utils.R;

import java.util.List;

/**
 * @Description: GameService
 * @Author: ahao
 * @Date: 2023/5/4 23:00
 **/

public interface GameService {

    R likeGetListByPage(GamePageSearch pageSearch);

    R saveInfo(Game game);

    R removeInfo(Integer gameId);

    R removeList(List<Game> gameList);

    R updateInfo(Game game);
}

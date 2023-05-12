package com.ahao.admin.controller.game;

import com.ahao.admin.param.game.GamePageSearch;
import com.ahao.admin.pojo.Game;
import com.ahao.admin.service.GameService;
import com.ahao.admin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: GameController
 * @Author: ahao
 * @Date: 2023/5/4 22:41
 **/

@RestController
@RequestMapping("game")
public class GameController {

    @Autowired
    private GameService gameService;

    /**
     * @Description: 分页+模糊查询
     * @param pageSearch
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("likeGetListByPage")
    public R likeGetListByPage(@RequestBody GamePageSearch pageSearch){
        return gameService.likeGetListByPage(pageSearch);
    }

    /**
     * @Description: 新增
     * @param game
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("saveInfo")
    public R saveInfo(@RequestBody Game game){
        return gameService.saveInfo(game);
    }

    /**
     * @Description: 删除
     * @param gameId
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("removeInfo")
    public R removeInfo(@RequestParam("game_id") Integer gameId){
        return gameService.removeInfo(gameId);
    }

    /**
     * @Description: 批量删除
     * @param gameList
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("removeList")
    public R removeList(@RequestBody List<Game> gameList){
        return gameService.removeList(gameList);
    }

    /**
     * @Description: 修改
     * @param game
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("updateInfo")
    public R updateInfo(@RequestBody Game game){
        return gameService.updateInfo(game);
    }

}

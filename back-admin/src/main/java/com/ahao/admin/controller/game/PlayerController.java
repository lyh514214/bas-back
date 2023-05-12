package com.ahao.admin.controller.game;

import com.ahao.admin.param.game.PlayerPageSearch;
import com.ahao.admin.pojo.Player;
import com.ahao.admin.service.PlayerService;
import com.ahao.admin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: PlayerController
 * @Author: ahao
 * @Date: 2023/5/5 18:31
 **/

@RestController
@RequestMapping("player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    /**
     * @Description: 获取列表
     * @return com.ahao.admin.utils.R
    **/
    @GetMapping("getList")
    public R getList(){
        return playerService.getList();
    }

    /**
     * @Description: 分页+模糊
     * @param pageSearch
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("likeGetListByPage")
    public R likeGetListByPage(@RequestBody PlayerPageSearch pageSearch){
        return playerService.likeGetListByPage(pageSearch);
    }

    /**
     * @Description: 保存
     * @param player
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("saveInfo")
    public R saveInfo(@RequestBody Player player){
        return playerService.saveInfo(player);
    }

    /**
     * @Description: 删除
     * @param id
     * @return com.ahao.admin.utils.R
     **/
    @PostMapping("removeInfo")
    public R removeInfo(@RequestParam("player_id") Integer id){
        return playerService.removeInfo(id);
    }

    /**
     * @Description: 批量删除
     * @param playerList
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("removeList")
    public R removeList(@RequestBody  List<Player> playerList){
        return playerService.removeList(playerList);
    }

    /**
     * @Description: 修改
     * @param player
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("updateInfo")
    public R updateInfo(@RequestBody Player player){
        return playerService.updateInfo(player);
    }




}

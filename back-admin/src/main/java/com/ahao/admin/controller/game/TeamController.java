package com.ahao.admin.controller.game;

import com.ahao.admin.param.game.TeamPageSearch;
import com.ahao.admin.pojo.Team;
import com.ahao.admin.service.TeamService;
import com.ahao.admin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: TeamComtroller
 * @Author: ahao
 * @Date: 2023/5/5 16:17
 **/

@RestController
@RequestMapping("team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    /**
     * @Description: 列表
     * @return com.ahao.admin.utils.R
    **/
    @GetMapping("getList")
    public R getList(){
        return teamService.getList();
    }

    /**
     * @Description: 模糊+分页
     * @param pageSearch
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("likeGetListByPage")
    public R likeGetListByPage(@RequestBody TeamPageSearch pageSearch){
        return teamService.likeGetListByPage(pageSearch);
    }
    
    /**
     * @Description: 新增
     * @param team
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("saveInfo")
    public R saveInfo(@RequestBody Team team){
        return teamService.saveInfo(team);
    }

    /**
     * @Description: 修改
     * @param team
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("updateInfo")
    public R updateInfo(@RequestBody Team team){
        return teamService.updateInfo(team);
    }


    /**
     * @Description: 删除
     * @param teamId
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("removeInfo")
    public R removeInfo(@RequestParam("team_id") Integer teamId){
        return teamService.removeInfo(teamId);
    }

}

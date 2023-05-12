package com.ahao.admin.service.impl;

import com.ahao.admin.mapper.PlayerMapper;
import com.ahao.admin.mapper.TeamMapper;
import com.ahao.admin.param.game.PlayerPageSearch;
import com.ahao.admin.pojo.Player;
import com.ahao.admin.pojo.Team;
import com.ahao.admin.service.PlayerService;
import com.ahao.admin.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: PlayerServiceImpl
 * @Author: ahao
 * @Date: 2023/5/5 18:32
 **/

@Service
@Slf4j
public class PlayerServiceImpl extends ServiceImpl<PlayerMapper, Player> implements PlayerService {

    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private TeamMapper teamMapper;

    /**
     * @Description: 获取列表
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R getList() {
        List<Player> playerList = playerMapper.selectList(null);
        log.info("getList业务完成，结果为：{}条",playerList.size());
        return R.ok("获取列表成功！",playerList);
    }

    /**
     * @Description: 分页+模糊
     * @param pageSearch
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R likeGetListByPage(PlayerPageSearch pageSearch) {
        IPage<Player> page = new Page<>(pageSearch.getCurrentPage(), pageSearch.getPageSize());
        QueryWrapper<Player> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(pageSearch.getPlayerName())){
            wrapper.like("player_name",pageSearch.getPlayerName());
        }
        if (pageSearch.getTeamId() != null){
            wrapper.eq("team_id",pageSearch.getTeamId());
        }
        playerMapper.selectPage(page,wrapper);
        List<Player> playerList = page.getRecords();
        for (Player player : playerList) {
            Team team = teamMapper.selectById(player.getTeamId());
            player.setTeamName(team.getTeamName());
        }
        return R.ok("查询成功！",playerList,page.getTotal());
    }

    /**
     * @Description: 保存
     * @param player
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R saveInfo(Player player) {
        int insert = playerMapper.insert(player);
        if (insert == 0){
            return R.fail("保存失败！");
        }
        log.info("saveInfo业务完成，结果为：{}条",insert);
        return R.ok("保存成功！");
    }

    /**
     * @Description: 删除
     * @param id
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R removeInfo(Integer id) {
        int delete = playerMapper.deleteById(id);
        if (delete == 0){
            return R.fail("删除失败！");
        }
        log.info("removeInfo业务完成，结果为：{}条",delete);
        return R.ok("删除成功！");
    }

    /**
     * @Description: 批量删除
     * @param playerList
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R removeList(List<Player> playerList) {
        int delete = playerMapper.deleteBatchIds(playerList);
        if (delete == 0){
            return R.fail("删除失败！");
        }
        log.info("removeList业务完成，结果为：{}条",delete);
        return R.ok("删除成功！共"+delete+"条记录");
    }

    /**
     * @Description: 修改
     * @param player
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R updateInfo(Player player) {
        int update = playerMapper.updateById(player);
        if (update == 0){
            return R.fail("修改失败！");
        }
        log.info("updateInfo业务完成，结果为：{}条",update);
        return R.ok("修改成功！");
    }


}

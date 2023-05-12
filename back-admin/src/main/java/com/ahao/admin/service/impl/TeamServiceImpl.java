package com.ahao.admin.service.impl;

import com.ahao.admin.mapper.PlayerMapper;
import com.ahao.admin.mapper.TeamMapper;
import com.ahao.admin.param.game.TeamPageSearch;
import com.ahao.admin.pojo.Player;
import com.ahao.admin.pojo.Team;
import com.ahao.admin.service.TeamService;
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
 * @Description: TeamServiceImpl
 * @Author: ahao
 * @Date: 2023/5/5 16:19
 **/

@Service
@Slf4j
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team> implements TeamService{

    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private PlayerMapper playerMapper;

    /**
     * @Description: 列表
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R getList() {
        List<Team> teamList = teamMapper.selectList(null);
        log.info("getList业务完成，结果为：{}条",teamList.size());
        return R.ok("获取列表成功！",teamList);
    }

    /**
     * @Description: 模糊+分页
     * @param pageSearch
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R likeGetListByPage(TeamPageSearch pageSearch) {
        IPage<Team> page = new Page<Team>(pageSearch.getCurrentPage(), pageSearch.getPageSize());
        QueryWrapper<Team> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(pageSearch.getTeamName())){
            wrapper.like("team_name",pageSearch.getTeamName());
        }
        teamMapper.selectPage(page,wrapper);
        List<Team> records = page.getRecords();
        return R.ok("查询成功！",page.getRecords(),page.getTotal());
    }

    /**
     * @Description: 新增
     * @param team
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R saveInfo(Team team) {
        QueryWrapper<Team> wrapper = new QueryWrapper<>();
        wrapper.eq("team_name",team.getTeamName());
        Team old = teamMapper.selectOne(wrapper);
        if (old != null){
            return R.fail("该球队已存在");
        }
        int insert = teamMapper.insert(team);
        if (insert == 0 ){
            return R.fail("保存失败！");
        }
        log.info("saveInfo业务完成，结果为：{}",insert);
        return R.ok("保存成功！");
    }

    /**
     * @Description: 修改
     * @param team
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R updateInfo(Team team) {
        QueryWrapper<Team> wrapper = new QueryWrapper<>();
        wrapper.eq("team_name",team.getTeamName());
        Team old = teamMapper.selectOne(wrapper);
        if (old != null){
            return R.fail("该球队已存在");
        }
        int update = teamMapper.updateById(team);
        if (update == 0){
            return R.fail("修改失败！");
        }
        log.info("updateInfo业务完成，结果为：{}",update);
        return R.ok("修改成功！");
    }

    /**
     * @Description: 删除
     * @param teamId
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R removeInfo(Integer teamId) {
        if (teamId == 1){
            return R.fail("不能删除！");
        }
        // 将要被删除球队 里面的球员 的team_id设为到id为1
        QueryWrapper<Player> playerQueryWrapper = new QueryWrapper<>();
        playerQueryWrapper.eq("team_id",teamId);
        List<Player> playerList = playerMapper.selectList(playerQueryWrapper);
        for (Player player : playerList) {
            player.setTeamId(1);
            playerMapper.updateById(player);
        }
        int delete = teamMapper.deleteById(teamId);
        if (delete == 0){
            return R.fail("删除失败！");
        }
        log.info("removeInfo业务完成，结果为：{}",delete);
        return R.ok("删除成功！");
    }


}

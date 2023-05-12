package com.ahao.admin.service.impl;

import com.ahao.admin.mapper.GameMapper;
import com.ahao.admin.param.game.GamePageSearch;
import com.ahao.admin.pojo.Game;
import com.ahao.admin.service.GameService;
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
import java.util.Objects;

/**
 * @Description: GameServiceImpl
 * @Author: ahao
 * @Date: 2023/5/4 23:01
 **/

@Service
@Slf4j
public class GameServiceImpl extends ServiceImpl<GameMapper, Game> implements GameService {

    @Autowired
    private GameMapper gameMapper;

    /**
     * @Description: 分页+模糊
     * @param pageSearch
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R likeGetListByPage(GamePageSearch pageSearch) {
        IPage<Game> page = new Page<Game>(pageSearch.getCurrentPage(), pageSearch.getPageSize());
        QueryWrapper<Game> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(pageSearch.getGameName())){
            wrapper.like("game_name",pageSearch.getGameName());
        }
        if (pageSearch.getCreateTime() != null){
            wrapper.eq("create_time",pageSearch.getCreateTime());
        }
        gameMapper.selectPage(page,wrapper);
        return R.ok("查询成功！",page.getRecords(),page.getTotal());
    }

    /**
     * @Description: 新增
     * @param game
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R saveInfo(Game game) {
        if (Objects.equals(game.getATeamName(), game.getBTeamName())){
            return R.fail("惊呆了！" + game.getATeamName() + "  自己跟自己打！？");
        }
        QueryWrapper<Game> wrapper = new QueryWrapper<>();
        wrapper.eq("court_name",game.getCourtName());
        wrapper.eq("A_team_name",game.getATeamName());
        wrapper.eq("B_team_name",game.getBTeamName());
        wrapper.eq("create_time",game.getCreateTime());
        Game one = gameMapper.selectOne(wrapper);
        if (one != null){
            return R.fail("同球场、同队伍且同时间的比赛，录入失败！");
        }
        int insert = gameMapper.insert(game);
        if (insert == 0){
            return R.fail("录入错误！");
        }
        log.info("saveInfo业务完成");
        return R.ok("录入成功！");
    }

    /**
     * @Description: 删除
     * @param gameId
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R removeInfo(Integer gameId) {
        int delete = gameMapper.deleteById(gameId);
        if (delete == 0){
            return R.fail("删除失败！");
        }
        log.info("removeInfo业务完成");
        return R.ok("删除成功！");
    }

    /**
     * @Description: 批量删除
     * @param gameList
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R removeList(List<Game> gameList) {
        int delete = gameMapper.deleteBatchIds(gameList);
        if (delete == 0){
            return R.fail("删除失败！");
        }
        log.info("removeList业务完成，结果为：{}条",delete);
        return R.ok("删除成功！共"+delete+"条");
    }

    /**
     * @Description: 修改
     * @param game
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R updateInfo(Game game) {
        if (Objects.equals(game.getATeamName(), game.getBTeamName())){
            return R.fail("惊呆了！" + game.getATeamName() + "  自己跟自己打！？");
        }
        int update = gameMapper.updateById(game);
        if (update == 0) {
            return R.fail("修改失败！");
        }
        log.info("updateInfo业务完成，结果为：{}条",update);
        return R.ok("修改成功！");
    }


}

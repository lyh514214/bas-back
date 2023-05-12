package com.ahao.admin.service.impl;

import com.ahao.admin.mapper.CourtMapper;
import com.ahao.admin.mapper.CourtRegionMapper;
import com.ahao.admin.pojo.Court;
import com.ahao.admin.pojo.CourtRegion;
import com.ahao.admin.service.CourtRegionService;
import com.ahao.admin.utils.PageParams;
import com.ahao.admin.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: CourtRegionServiceImpl
 * @Author: ahao
 * @Date: 2023/5/3 16:49
 **/

@Service
@Slf4j
public class CourtRegionServiceImpl extends ServiceImpl<CourtRegionMapper, CourtRegion> implements CourtRegionService {

    @Autowired
    private CourtRegionMapper courtRegionMapper;
    @Autowired
    private CourtMapper courtMapper;

    /**
     * @Description: 获取列表
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R getList() {
        List<CourtRegion> courtRegions =
                courtRegionMapper.selectList(null);
        log.info("getList业务完成，结果为{}条",courtRegions.size());
        return R.ok("查询成功！",courtRegions);
    }

    /**
     * @Description: 分页
     * @param pageParams
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R byPage(PageParams pageParams) {
        IPage<CourtRegion> page = new Page<CourtRegion>(pageParams.getCurrentPage(), pageParams.getPageSize());
        courtRegionMapper.selectPage(page, null);
        log.info("byPage业务完成，结果为{}条",page.getRecords().size());
        return R.ok("查询成功！",page.getRecords(),page.getSize());
    }

    /**
     * @Description: 新增
     * @param courtRegion
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R saveInfo(CourtRegion courtRegion) {
        CourtRegion one = courtRegionMapper.selectOne(
                new QueryWrapper<CourtRegion>().eq("court_region_name", courtRegion.getCourtRegionName()));
        if (one != null) {
            return R.fail("添加失败，该区域名称已存在！");
        }
        int insert = courtRegionMapper.insert(courtRegion);
        if (insert == 0){
            return R.fail("保存失败！");
        }
        log.info("saveInfo业务完成，结果为{}条",insert);
        return R.ok("保存成功！");
    }

    /**
     * @Description: 修改
     * @param courtRegion
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R updateInfo(CourtRegion courtRegion) {
        CourtRegion one = courtRegionMapper.selectOne(
                new QueryWrapper<CourtRegion>().eq("court_region_name", courtRegion.getCourtRegionName()));
        if (one != null) {
            return R.fail("添加失败，该区域名称已存在！");
        }
        int update = courtRegionMapper.updateById(courtRegion);
        if (update == 0){
            return R.fail("修改失败！");
        }
        log.info("updateInfo业务完成，结果为{}条",update);
        return R.ok("修改成功！");
    }

    /**
     * @Description: 删除
     * @param courtRegionId
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R removeInfo(Integer courtRegionId) {
        int delCourt = courtMapper.delete(new QueryWrapper<Court>().eq("court_region_id",courtRegionId));
        int delRegion = courtRegionMapper.deleteById(courtRegionId);
        if (delRegion == 0){
            return R.fail("删除失败！");
        }
        log.info("removeInfo业务完成，结果为{}条，球场信息被迫删除{}条",delRegion,delCourt);
        return R.ok("删除成功！");
    }

    /**
     * @Description: 获取某条区域信息
     * @param courtRegionId
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R getOne(Integer courtRegionId) {
        CourtRegion courtRegion = courtRegionMapper.selectById(courtRegionId);
        if (courtRegion == null){
            return R.fail("没有该区域信息！");
        }
        return R.ok("查询成功！",courtRegion);
    }


}

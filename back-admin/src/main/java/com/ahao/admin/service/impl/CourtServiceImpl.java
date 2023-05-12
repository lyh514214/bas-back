package com.ahao.admin.service.impl;

import com.ahao.admin.mapper.CourtCategoryMapper;
import com.ahao.admin.mapper.CourtMapper;
import com.ahao.admin.mapper.CourtRegionMapper;
import com.ahao.admin.param.court.CourtPageAndSearch;
import com.ahao.admin.pojo.Court;
import com.ahao.admin.pojo.CourtCategory;
import com.ahao.admin.pojo.CourtRegion;
import com.ahao.admin.service.CourtService;
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
 * @Description: CourtServiceImpl
 * @Author: ahao
 * @Date: 2023/5/3 16:30
 **/

@Service
@Slf4j
public class CourtServiceImpl extends ServiceImpl<CourtMapper, Court> implements CourtService {

    @Autowired
    private CourtMapper courtMapper;
    @Autowired
    private CourtRegionMapper courtRegionMapper;
    @Autowired
    private CourtCategoryMapper courtCategoryMapper;

    /**
     * @Description: 列表
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R getList() {
        List<Court> courtList = courtMapper.selectList(null);
        log.info("getList业务完成，结果为：{}条",courtList.size());
        return R.ok("获取列表成功！",courtList);
    }

    /**
     * @Description: 分页+模糊
     * @param param
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R likeGetListByPage(CourtPageAndSearch param) {
        IPage<Court> page = new Page<>(param.getCurrentPage(),param.getPageSize());
        QueryWrapper<Court> courtQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(param.getCourtName())) {
            courtQueryWrapper.like("court_name",param.getCourtName());
        }
        if (param.getCourtCategoryId() != null ) {
            courtQueryWrapper.eq("court_category_id",param.getCourtCategoryId());
        }
        if (param.getCourtRegionId() != null) {
            courtQueryWrapper.eq("court_region_id",param.getCourtRegionId());
        }
        courtMapper.selectPage(page,courtQueryWrapper);
        List<Court> courtList = page.getRecords();
        long total = page.getTotal();
        // 区域 类别转换
        for (Court court : courtList) {
            Integer courtCategoryId = court.getCourtCategoryId();
            Integer courtRegionId = court.getCourtRegionId();
            CourtCategory courtCategory
                    = courtCategoryMapper.selectOne(new QueryWrapper<CourtCategory>().eq("court_category_id", courtCategoryId));
            court.setCourtCategory(courtCategory.getCourtCategoryName());    //类别转化
            CourtRegion courtRegion
                    = courtRegionMapper.selectOne(new QueryWrapper<CourtRegion>().eq("court_region_id", courtRegionId));
            court.setCourtRegion(courtRegion.getCourtRegionName());      //区域转化
        }
        log.info("likeGetListByPage业务完成，结果为{}条",courtList.size());
        return R.ok("查询成功！",courtList,total);
    }

    /**
     * @Description: 新增
     * @param court
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R saveCourtInfo(Court court) {
        if (court.getCourtName() == null || court.getCourtCategoryId() == null || court.getCourtRegionId() == null){
            return R.fail("请完善表单后再提交！");
        }
        Court old = courtMapper.selectOne(new QueryWrapper<Court>().eq("court_name", court.getCourtName()));
        if (old != null){
            return R.fail("重复命名！");
        }
        int insert = courtMapper.insert(court);
        log.info("saveCourtInfo业务完成，结果为：{}条",insert);
        if (insert == 0){
            return R.fail("保存失败！");
        }
        return R.ok("保存成功！");
    }

    /**
     * @Description: 删除
     * @param courtId
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R removeCourtInfo(Integer courtId) {
        int i = courtMapper.deleteById(courtId);
        log.info("removeCourtInfo业务完成，结果为：{}条",i);
        if (i == 0){
            return R.fail("删除失败！");
        }
        return R.ok("删除成功！");
    }

    /**
     * @Description: 修改
     * @param court
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R updateCourtInfo(Court court) {
        int i = courtMapper.updateById(court);
        log.info("updateCourtInfo业务完成，结果为：{}条",i);
        if (i == 0) {
            return R.fail("修改失败！");
        }
        return R.ok("修改成功！");
    }

    /**
     * @Description: 根据区域查找球场
     * @param regionId
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R getListByRegion(Integer regionId) {
        QueryWrapper<Court> wrapper = new QueryWrapper<>();
        wrapper.eq("court_region_id", regionId);
        wrapper.eq("court_status",1);
        List<Court> courtList = courtMapper.selectList(wrapper);
        for (Court court : courtList) {
            Integer categoryId = court.getCourtCategoryId();
            if (categoryId != null){
                CourtCategory one
                        = courtCategoryMapper.selectOne(new QueryWrapper<CourtCategory>().eq("court_category_id", categoryId));
                court.setCourtCategory(one.getCourtCategoryName());
            }else {
                court.setCourtCategory("无");
            }
        }
        if (courtList.size() == 0){
            return R.fail("该区域没有空闲球场！");
        }
        return R.ok("获取成功！",courtList);
    }


}

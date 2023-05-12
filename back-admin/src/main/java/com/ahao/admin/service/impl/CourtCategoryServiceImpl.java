package com.ahao.admin.service.impl;

import com.ahao.admin.mapper.CourtCategoryMapper;
import com.ahao.admin.mapper.CourtMapper;
import com.ahao.admin.pojo.Court;
import com.ahao.admin.pojo.CourtCategory;
import com.ahao.admin.service.CourtCategoryService;
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
 * @Description: CourtCategoryServiceImpl
 * @Author: ahao
 * @Date: 2023/5/3 16:46
 **/

@Service
@Slf4j
public class CourtCategoryServiceImpl extends ServiceImpl<CourtCategoryMapper, CourtCategory> implements CourtCategoryService {

    @Autowired
    private CourtCategoryMapper courtCategoryMapper;
    @Autowired
    private CourtMapper courtMapper;

    /**
     * @Description: 获取列表
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R getList() {
        List<CourtCategory> courtCategories =
                courtCategoryMapper.selectList(null);
        log.info("getList业务完成，结果为{}条",courtCategories.size());
        return R.ok("查询成功！",courtCategories);
    }

    /**
     * @Description: 分页
     * @param pageParams
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R byPage(PageParams pageParams) {
        IPage<CourtCategory> page = new Page<>(pageParams.getCurrentPage(),pageParams.getPageSize());
        courtCategoryMapper.selectPage(page,null);
        log.info("byPage业务完成，结果为{}条",page.getRecords().size());
        return R.ok("查询成功！",page.getRecords(),page.getTotal());
    }

    /**
     * @Description: 新增
     * @param courtCategory
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R saveInfo(CourtCategory courtCategory) {
        String courtCategoryName = courtCategory.getCourtCategoryName();
        CourtCategory one = courtCategoryMapper.selectOne(
                new QueryWrapper<CourtCategory>().eq("court_category_name", courtCategoryName));
        if (one != null){
            return R.fail("新增失败，已存在该球场！");
        }
        int insert = courtCategoryMapper.insert(courtCategory);
        if (insert == 0){
            return R.fail("新增失败！");
        }
        log.info("saveInfo业务完成，结果为{}条",insert);
        return R.ok("新增成功！");
    }

    /**
     * @Description: 修改
     * @param courtCategory
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R updateInfo(CourtCategory courtCategory) {
        String courtCategoryName = courtCategory.getCourtCategoryName();
        CourtCategory one = courtCategoryMapper.selectOne(
                new QueryWrapper<CourtCategory>().eq("court_category_name", courtCategoryName));
        if (one != null){
            return R.fail("修改失败，该球场名称已被使用！");
        }
        int update = courtCategoryMapper.updateById(courtCategory);
        if (update == 0){
            return R.fail("修改失败！");
        }
        log.info("updateInfo业务完成，结果为{}条",update);
        return R.ok("修改成功！");
    }

    /**
     * @Description: 删除
     * @param courtCategoryId
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R removeInfo(Integer courtCategoryId) {
        int delCourt = courtMapper.delete(new QueryWrapper<Court>().eq("court_category_id",courtCategoryId));
        int delCourtCategory = courtCategoryMapper.deleteById(courtCategoryId);
        if (delCourtCategory == 0){
            return R.fail("删除失败！");
        }
        log.info("removeInfo业务完成，结果为{}条，球场信息被迫删除{}条",delCourtCategory,delCourt);
        return R.ok("删除成功！");
    }


}

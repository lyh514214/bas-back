package com.ahao.admin.service.impl;

import com.ahao.admin.mapper.NewsMapper;
import com.ahao.admin.pojo.News;
import com.ahao.admin.service.NewsService;
import com.ahao.admin.utils.PageParams;
import com.ahao.admin.utils.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: CarouselServiceImpl
 * @Author: ahao
 * @Date: 2023/5/8 18:46
 **/

@Service
@Slf4j
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    /**
     * @Description: 获取列表
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R getList() {
        return R.ok("查询成功！",newsMapper.selectList(null));
    }

    @Override
    public R getListByPage(PageParams params) {
        IPage<News> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        newsMapper.selectPage(page,null);
        List<News> newsList = page.getRecords();
        long total = page.getTotal();
        return R.ok("查询成功",newsList,total);
    }

    @Override
    public R saveInfo(News news) {
        int insert = newsMapper.insert(news);
        if (insert == 0){
            return R.fail("保存失败！");
        }
        return R.ok("保存成功！");
    }

    @Override
    public R removeInfo(Integer id) {
        int delete = newsMapper.deleteById(id);
        if(delete == 0){
            return R.fail("删除失败");
        }
        return R.ok("删除成功！");
    }

    @Override
    public R updateInfo(News news) {
        int update = newsMapper.updateById(news);
        if (update == 0){
            return R.fail("更新失败！");
        }
        return R.ok("更新成功！");
    }


}

package com.ahao.admin.service;

import com.ahao.admin.pojo.News;
import com.ahao.admin.utils.PageParams;
import com.ahao.admin.utils.R;

/**
 * @Description: CarouselService
 * @Author: ahao
 * @Date: 2023/5/8 18:46
 **/

public interface NewsService {
    R getList();

    R getListByPage(PageParams params);

    R saveInfo(News news);

    R removeInfo(Integer id);

    R updateInfo(News news);
}

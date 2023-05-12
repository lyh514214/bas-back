package com.ahao.admin.service;

import com.ahao.admin.pojo.CourtCategory;
import com.ahao.admin.utils.PageParams;
import com.ahao.admin.utils.R;

/**
 * @Description: CourtCategoryService
 * @Author: ahao
 * @Date: 2023/5/3 16:46
 **/

public interface CourtCategoryService {

    R getList();

    R byPage(PageParams pageParams);

    R saveInfo(CourtCategory courtCategory);

    R updateInfo(CourtCategory courtCategory);

    R removeInfo(Integer courtCategoryId);
}

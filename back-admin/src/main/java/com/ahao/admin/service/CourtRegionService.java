package com.ahao.admin.service;

import com.ahao.admin.pojo.CourtRegion;
import com.ahao.admin.utils.PageParams;
import com.ahao.admin.utils.R;

/**
 * @Description: CourtRegionService
 * @Author: ahao
 * @Date: 2023/5/3 16:48
 **/

public interface CourtRegionService {

    R getList();

    R byPage(PageParams pageParams);

    R saveInfo(CourtRegion courtRegion);

    R updateInfo(CourtRegion courtRegion);

    R removeInfo(Integer courtRegionId);

    R getOne(Integer courtRegionId);
}

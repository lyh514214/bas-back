package com.ahao.admin.service;

import com.ahao.admin.param.court.CourtPageAndSearch;
import com.ahao.admin.pojo.Court;
import com.ahao.admin.utils.R;

/**
 * @Description: CourtService
 * @Author: ahao
 * @Date: 2023/5/3 16:29
 **/
public interface CourtService {

    R getList();

    R likeGetListByPage(CourtPageAndSearch courtPageAndSearch);

    R saveCourtInfo(Court court);

    R removeCourtInfo(Integer courtId);

    R updateCourtInfo(Court court);

    R getListByRegion(Integer regionId);
}

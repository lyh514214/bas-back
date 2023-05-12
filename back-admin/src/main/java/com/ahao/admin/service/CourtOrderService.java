package com.ahao.admin.service;

import com.ahao.admin.param.order.CourtOrderPageParam;
import com.ahao.admin.pojo.CourtOrder;
import com.ahao.admin.utils.R;

import java.util.List;

/**
 * @Description: CourtOrderService
 * @Author: ahao
 * @Date: 2023/5/9 15:16
 **/

public interface CourtOrderService {

    R saveOrder(CourtOrder courtOrder);

    R getListByPage(CourtOrderPageParam param);

    R removeInfo(Integer id);

    R removeList(List<CourtOrder> courtOrderList);

    R beyCourt(Integer id);
}

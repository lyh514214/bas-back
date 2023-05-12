package com.ahao.admin.service;

import com.ahao.admin.param.order.EquipmentBuyOrderMakeParam;
import com.ahao.admin.param.order.EquipmentBuyOrderParam;
import com.ahao.admin.pojo.EquipmentBuyOrder;
import com.ahao.admin.utils.R;

import java.util.List;

/**
 * @Description: EquipmentBuyOrderService
 * @Author: ahao
 * @Date: 2023/5/11 16:13
 **/

public interface EquipmentBuyOrderService {

    R submit(EquipmentBuyOrderMakeParam param);

    R getListByPage(EquipmentBuyOrderParam equipmentBuyOrderParam);

    R removeInfo(Integer id);

    R removeList(List<EquipmentBuyOrder> equipmentBuyOrderList);
}

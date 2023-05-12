package com.ahao.admin.service;

import com.ahao.admin.param.order.EquipmentRentOrderMakeParam;
import com.ahao.admin.param.order.EquipmentRentOrderParam;
import com.ahao.admin.pojo.EquipmentRentOrder;
import com.ahao.admin.utils.R;

import java.util.List;

/**
 * @Description: EquipmentRentOrderService
 * @Author: ahao
 * @Date: 2023/5/11 0:18
 **/


public interface EquipmentRentOrderService {

    R submit(EquipmentRentOrderMakeParam param);

    R getListByPage(EquipmentRentOrderParam equipmentRentOrderParam);

    R beyEquipment(Integer id, Integer number);

    R removeInfo(Integer id);

    R removeList(List<EquipmentRentOrder> equipmentRentOrderList);
}

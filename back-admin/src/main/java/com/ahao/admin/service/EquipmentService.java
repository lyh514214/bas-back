package com.ahao.admin.service;

import com.ahao.admin.param.equipment.EquipmentPageSearch;
import com.ahao.admin.pojo.Equipment;
import com.ahao.admin.utils.R;

/**
 * @Description: EquipmentService
 * @Author: ahao
 * @Date: 2023/5/7 16:34
 **/
public interface EquipmentService {

    R likeGetListByPage(EquipmentPageSearch pageSearch);

    R saveInfo(Equipment equipment);

    R updateInfo(Equipment equipment);

    R removeInfo(Integer id);
}

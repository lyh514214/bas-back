package com.ahao.admin.controller.equipment;

import com.ahao.admin.param.equipment.EquipmentPageSearch;
import com.ahao.admin.pojo.Equipment;
import com.ahao.admin.service.EquipmentService;
import com.ahao.admin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: EquipmentController
 * @Author: ahao
 * @Date: 2023/5/7 16:55
 **/

@RestController
@RequestMapping("equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    /**
     * @Description: 分页+模糊
     * @param pageSearch
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("likeGetListByPage")
    public R likeGetListByPage(@RequestBody EquipmentPageSearch pageSearch){
        return equipmentService.likeGetListByPage(pageSearch);
    }

    /**
     * @Description: 保存
     * @param equipment
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("saveInfo")
    public R saveInfo(@RequestBody Equipment equipment){
        return equipmentService.saveInfo(equipment);
    }

    /**
     * @Description: 修改
     * @param equipment
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("updateInfo")
    public R updateInfo(@RequestBody Equipment equipment){
        return equipmentService.updateInfo(equipment);
    }

    /**
     * @Description: 删除
     * @param id
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("removeInfo")
    public R removeInfo(@RequestParam("equipment_id") Integer id){
        return equipmentService.removeInfo(id);
    }


}

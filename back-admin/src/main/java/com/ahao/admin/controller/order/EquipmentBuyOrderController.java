package com.ahao.admin.controller.order;

import com.ahao.admin.param.order.EquipmentBuyOrderMakeParam;
import com.ahao.admin.param.order.EquipmentBuyOrderParam;
import com.ahao.admin.pojo.EquipmentBuyOrder;
import com.ahao.admin.service.EquipmentBuyOrderService;
import com.ahao.admin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: EquipmentBuyOrderController
 * @Author: ahao
 * @Date: 2023/5/11 16:04
 **/

@RestController
@RequestMapping("equipmentBuyOrder")
public class EquipmentBuyOrderController {

    @Autowired
    private EquipmentBuyOrderService equipmentBuyOrderService;

    /**
     * @Description: 提交订单
     * @param param
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("submit")
    public R submit(@RequestBody EquipmentBuyOrderMakeParam param){
        return equipmentBuyOrderService.submit(param);
    }

    /**
     * @Description: 分页
     * @param equipmentBuyOrderParam
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("getListByPage")
    public R getListByPage(@RequestBody EquipmentBuyOrderParam equipmentBuyOrderParam){
        return equipmentBuyOrderService.getListByPage(equipmentBuyOrderParam);
    }

    /**
     * @Description: 删除
     * @param id
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("removeInfo")
    public R removeInfo(@RequestParam("order_id") Integer id){
        return equipmentBuyOrderService.removeInfo(id);
    }


    /**
     * @Description: 批量删除
     * @param equipmentBuyOrderList
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("removeList")
    public R removeList(@RequestBody List<EquipmentBuyOrder> equipmentBuyOrderList){
        return equipmentBuyOrderService.removeList(equipmentBuyOrderList);
    }





}

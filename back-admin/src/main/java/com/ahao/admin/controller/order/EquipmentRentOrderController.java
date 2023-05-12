package com.ahao.admin.controller.order;

import com.ahao.admin.param.order.EquipmentRentOrderMakeParam;
import com.ahao.admin.param.order.EquipmentRentOrderParam;
import com.ahao.admin.pojo.EquipmentRentOrder;
import com.ahao.admin.service.EquipmentRentOrderService;
import com.ahao.admin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: EquipmentRentOrderController
 * @Author: ahao
 * @Date: 2023/5/11 0:16
 **/

@RestController
@RequestMapping("equipmentRentOrder")
public class EquipmentRentOrderController {

    @Autowired
    private EquipmentRentOrderService equipmentRentOrderService;

    /**
     * @Description: 提交租赁订单
     * @param param
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("submit")
    public R submit(@RequestBody EquipmentRentOrderMakeParam param){
        return equipmentRentOrderService.submit(param);
    }


    /**
     * @Description: 分页
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("getListByPage")
    public R getListByPage(@RequestBody EquipmentRentOrderParam equipmentRentOrderParam){
        return equipmentRentOrderService.getListByPage(equipmentRentOrderParam);
    }

    /**
     * @Description: 归还
     * @param id
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("beyEquipment")
    public R beyEquipment(@RequestParam("order_id") Integer id,@RequestParam("return_number") Integer number){
        return equipmentRentOrderService.beyEquipment(id,number);
    }

    /**
     * @Description: 删除一
     * @param id
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("removeInfo")
    public R removeInfo(@RequestParam("order_id") Integer id){
        return equipmentRentOrderService.removeInfo(id);
    }

    /**
     * @Description: 批量删除
     * @param equipmentRentOrderList
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("removeList")
    public R removeList(@RequestBody List<EquipmentRentOrder> equipmentRentOrderList){
        return equipmentRentOrderService.removeList(equipmentRentOrderList);
    }




}

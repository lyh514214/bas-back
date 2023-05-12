package com.ahao.admin.controller.order;

import com.ahao.admin.param.order.CourtOrderPageParam;
import com.ahao.admin.pojo.CourtOrder;
import com.ahao.admin.service.CourtOrderService;
import com.ahao.admin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: CourtOrderController
 * @Author: ahao
 * @Date: 2023/5/9 15:17
 **/

@RestController
@RequestMapping("courtOrder")
public class CourtOrderController {

    @Autowired
    private CourtOrderService courtOrderService;

    /**
     * @Description: 场地租赁 保存订单
     * @param courtOrder
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("saveOrder")
    public R saveOrder(@RequestBody CourtOrder courtOrder){
        return courtOrderService.saveOrder(courtOrder);
    }

    /**
     * @Description: 分页查询订单 并判断当前是否登录用户 定向查看订单
     * @param param
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("getListByPage")
    public R getListByPage(@RequestBody CourtOrderPageParam param){
        return courtOrderService.getListByPage(param);
    }

    /**
     * @Description: 删除某条
     * @param id
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("removeInfo")
    public R removeInfo(@RequestParam("court_order_id") Integer id){
        return courtOrderService.removeInfo(id);
    }

    /**
     * @Description: 批量删除
     * @param courtOrderList
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("removeList")
    public R removeList(@RequestBody List<CourtOrder> courtOrderList){
        return courtOrderService.removeList(courtOrderList);
    }

    /**
     * @Description: 退场
     * @param id
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("beyCourt")
    public R beyCourt(@RequestParam("court_order_id") Integer id){
        return courtOrderService.beyCourt(id);
    }
}

package com.ahao.admin.service.impl;

import com.ahao.admin.mapper.EquipmentMapper;
import com.ahao.admin.mapper.EquipmentRentOrderMapper;
import com.ahao.admin.mapper.UserMapper;
import com.ahao.admin.param.order.EquipmentRentOrderMakeParam;
import com.ahao.admin.param.order.EquipmentRentOrderParam;
import com.ahao.admin.pojo.Equipment;
import com.ahao.admin.pojo.EquipmentRentOrder;
import com.ahao.admin.pojo.User;
import com.ahao.admin.service.EquipmentRentOrderService;
import com.ahao.admin.utils.R;
import com.ahao.admin.utils.SnowFlakeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description: EquipmentRentOrderServiceImpl
 * @Author: ahao
 * @Date: 2023/5/11 0:18
 **/

@Service
@Transactional
@Slf4j
public class EquipmentRentOrderServiceImpl extends ServiceImpl<EquipmentRentOrderMapper, EquipmentRentOrder> implements EquipmentRentOrderService {

    @Autowired
    private EquipmentRentOrderMapper equipmentRentOrderMapper;
    @Autowired
    private EquipmentMapper equipmentMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * @Description: 添加订单
     * @param param
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R submit(EquipmentRentOrderMakeParam param) {
        if (param.getUserId() == null){
            return R.fail("请先登录再购买！");
        }
        String snowId = String.valueOf(new SnowFlakeUtil().nextId());  //雪花id
        List<EquipmentRentOrder> equipmentArray = param.getEquipmentArray();
        for (EquipmentRentOrder equipmentRentOrder : equipmentArray) {
            // 补充订单信息
            equipmentRentOrder.setSnowId(snowId);
            equipmentRentOrder.setUserId(param.getUserId());
            equipmentRentOrder.setUserName(param.getUserName());
            equipmentRentOrder.setCreateTime(param.getCreateTime());
            equipmentRentOrder.setTotalPrice(equipmentRentOrder.getRent()*equipmentRentOrder.getNumber());
            equipmentRentOrder.setReturned('0');
            // 减少器材库存
            Equipment equipment = equipmentMapper.selectOne(new QueryWrapper<Equipment>()
                    .eq("equipment_id", equipmentRentOrder.getEquipmentId()));
            if (equipment.getInventory() > equipmentRentOrder.getNumber()){
                equipment.setInventory(equipment.getInventory()-equipmentRentOrder.getNumber());
            }else {
                return R.fail(equipment.getEquipmentName()+"商品库存不足");
            }
            equipmentMapper.updateById(equipment);
            equipmentRentOrderMapper.insert(equipmentRentOrder);
        }
        return R.ok("创建订单成功！");


    }

    /**
     * @Description: 分页获取信息
     * @param param
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R getListByPage(EquipmentRentOrderParam param) {
        QueryWrapper<EquipmentRentOrder> wrapper = new QueryWrapper<>();
        IPage<EquipmentRentOrder> page = new Page<>(param.getCurrentPage(),param.getPageSize());
        if (param.getCurrentUserRoleId() == null || param.getCurrentUserRoleId()>2){
            wrapper.eq("user_id",param.getCurrentUserId());
        }
        equipmentRentOrderMapper.selectPage(page,wrapper);
        List<EquipmentRentOrder> equipmentRentOrderList = page.getRecords();
        for (EquipmentRentOrder equipmentRentOrder : equipmentRentOrderList) {
            User user = userMapper.selectById(equipmentRentOrder.getUserId());
            if (user != null){
                equipmentRentOrder.setUserName(user.getUserName());
            } else {
                equipmentRentOrder.setUserName("用户已不存在");
            }
        }
        return R.ok("查询成功！",equipmentRentOrderList,page.getTotal());
    }

    /**
     * @Description: 归还
     * @param id
     * @param number
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R beyEquipment(Integer id, Integer number) {
        EquipmentRentOrder equipmentRentOrder = equipmentRentOrderMapper.selectOne(
                new QueryWrapper<EquipmentRentOrder>().eq("order_id", id));
        if (equipmentRentOrder.getReturned() == '1'){
            return R.fail("该器材已近归还过");
        }
        // 更新库存
        Equipment equipment = equipmentMapper.selectOne(new QueryWrapper<Equipment>().eq("equipment_id", equipmentRentOrder.getEquipmentId()));
        equipment.setInventory(equipment.getInventory()+number);
        equipmentMapper.updateById(equipment);
        // 更新订单表 归还字段
        equipmentRentOrder.setReturnTime(new Date());
        equipmentRentOrder.setReturned('1');
        int updateReturned = equipmentRentOrderMapper.updateById(equipmentRentOrder);
        if (updateReturned == 0){
            return R.fail("归还失败！");
        }
        return R.ok("归还成功！");
    }

    /**
     * @Description: 删除订单
     * @param id
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R removeInfo(Integer id) {
        int delete = equipmentRentOrderMapper.deleteById(id);
        if (delete == 0){
            return R.fail("删除失败！");
        }
        return R.ok("删除成功！");
    }

    /**
     * @Description: 批量删除
     * @param equipmentRentOrderList
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R removeList(List<EquipmentRentOrder> equipmentRentOrderList) {
        int deleteBatchIds = equipmentRentOrderMapper.deleteBatchIds(equipmentRentOrderList);
        if (deleteBatchIds == 0){
            return R.fail("删除失败！");
        }
        return R.ok("删除成功，共删除"+deleteBatchIds+"条");
    }


}

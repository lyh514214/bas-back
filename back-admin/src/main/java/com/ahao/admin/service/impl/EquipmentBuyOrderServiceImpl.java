package com.ahao.admin.service.impl;

import com.ahao.admin.mapper.EquipmentBuyOrderMapper;
import com.ahao.admin.mapper.EquipmentMapper;
import com.ahao.admin.mapper.EquipmentRentOrderMapper;
import com.ahao.admin.mapper.UserMapper;
import com.ahao.admin.param.order.EquipmentBuyOrderMakeParam;
import com.ahao.admin.param.order.EquipmentBuyOrderParam;
import com.ahao.admin.pojo.Equipment;
import com.ahao.admin.pojo.EquipmentBuyOrder;
import com.ahao.admin.pojo.EquipmentRentOrder;
import com.ahao.admin.pojo.User;
import com.ahao.admin.service.EquipmentBuyOrderService;
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

import java.util.List;

/**
 * @Description: EquipmentBuyOrderServiceImpl
 * @Author: ahao
 * @Date: 2023/5/11 16:13
 **/

@Service
@Slf4j
@Transactional
public class EquipmentBuyOrderServiceImpl extends ServiceImpl<EquipmentBuyOrderMapper, EquipmentBuyOrder> implements EquipmentBuyOrderService {

    @Autowired
    private EquipmentBuyOrderMapper equipmentBuyOrderMapper;
    @Autowired
    private EquipmentMapper equipmentMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * @Description: 购买订单生成
     * @param param
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R submit(EquipmentBuyOrderMakeParam param) {
        if (param.getUserId() == null){
            return R.fail("请先登录进行再购买！");
        }
        String snowId = String.valueOf(new SnowFlakeUtil().nextId());  //雪花id
        List<EquipmentBuyOrder> equipmentArray = param.getEquipmentArray();
        for (EquipmentBuyOrder equipmentBuyOrder : equipmentArray) {
            // 补充订单信息
            equipmentBuyOrder.setSnowId(snowId);
            equipmentBuyOrder.setUserId(param.getUserId());
            equipmentBuyOrder.setUserName(param.getUserName());
            equipmentBuyOrder.setCreateTime(param.getCreateTime());
            equipmentBuyOrder.setTotalPrice(equipmentBuyOrder.getPrice()*equipmentBuyOrder.getNumber());
            
            // 减少器材库存
            Equipment equipment = equipmentMapper.selectOne(new QueryWrapper<Equipment>()
                    .eq("equipment_id", equipmentBuyOrder.getEquipmentId()));
            if (equipment.getInventory() > equipmentBuyOrder.getNumber()){
                equipment.setInventory(equipment.getInventory()-equipmentBuyOrder.getNumber());
            }else {
                return R.fail(equipment.getEquipmentName()+"商品库存不足");
            }
            equipmentMapper.updateById(equipment);
            equipmentBuyOrderMapper.insert(equipmentBuyOrder);
        }
        return R.ok("创建订单成功！");
    }

    /**
     * @Description: 分页
     * @param param
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R getListByPage(EquipmentBuyOrderParam param) {
        QueryWrapper<EquipmentBuyOrder> wrapper = new QueryWrapper<>();
        IPage<EquipmentBuyOrder> page = new Page<>(param.getCurrentPage(),param.getPageSize());
        if (param.getCurrentUserRoleId() == null || param.getCurrentUserRoleId()>2){
            wrapper.eq("user_id",param.getCurrentUserId());
        }
        equipmentBuyOrderMapper.selectPage(page,wrapper);
        List<EquipmentBuyOrder> equipmentRentOrderList = page.getRecords();
        for (EquipmentBuyOrder equipmentBuyOrder : equipmentRentOrderList) {
            User user = userMapper.selectById(equipmentBuyOrder.getUserId());
            if (user != null){
                equipmentBuyOrder.setUserName(user.getUserName());
            } else {
                equipmentBuyOrder.setUserName("用户已不存在");
            }
        }
        return R.ok("查询成功！",equipmentRentOrderList,page.getTotal());


    }

    /**
     * @Description: 删除
     * @param id
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R removeInfo(Integer id) {
        int delete = equipmentBuyOrderMapper.deleteById(id);
        if (delete == 0){
            return R.fail("删除失败！");
        }
        return R.ok("删除成功！");
    }

    /**
     * @Description: 批量删除
     * @param equipmentBuyOrderList
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R removeList(List<EquipmentBuyOrder> equipmentBuyOrderList) {
        int deleteBatchIds = equipmentBuyOrderMapper.deleteBatchIds(equipmentBuyOrderList);
        if (deleteBatchIds == 0){
            return R.fail("删除失败！");
        }
        return R.ok("删除成功，共"+deleteBatchIds+"条数据");
    }


}

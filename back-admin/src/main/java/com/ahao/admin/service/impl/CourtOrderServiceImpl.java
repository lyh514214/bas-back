package com.ahao.admin.service.impl;

import com.ahao.admin.mapper.CourtMapper;
import com.ahao.admin.mapper.CourtOrderMapper;
import com.ahao.admin.mapper.UserMapper;
import com.ahao.admin.param.order.CourtOrderPageParam;
import com.ahao.admin.pojo.Court;
import com.ahao.admin.pojo.CourtOrder;
import com.ahao.admin.pojo.User;
import com.ahao.admin.service.CourtOrderService;
import com.ahao.admin.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Description: CourtOrderServiceImpl
 * @Author: ahao
 * @Date: 2023/5/9 15:16
 **/

@Service
@Slf4j
public class CourtOrderServiceImpl extends ServiceImpl<CourtOrderMapper, CourtOrder> implements CourtOrderService {

    @Autowired
    private CourtOrderMapper courtOrderMapper;
    @Autowired
    private CourtMapper courtMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * @Description: 上传订单
     * @param courtOrder
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R saveOrder(CourtOrder courtOrder) {
        if (courtOrder.getCourtId() == null){
            return R.fail("请选择球场后再提交！");
        }
        Integer courtId = courtOrder.getCourtId();
        Court court = courtMapper.selectById(courtId);
        courtOrder.setRent(court.getRent());  // 获取租金

        int insert = courtOrderMapper.insert(courtOrder);   // 订单生成
        if (insert == 0){
            return R.fail("订单保存失败！");
        }
        // 更新球场状态
        court.setCourtStatus("0");
        courtMapper.updateById(court);
        return R.ok("订单生成成功！");
    }

    /**
     * @Description: 分页查询订单 并判断当前是否登录用户 定向查看订单
     * @param param
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R getListByPage(CourtOrderPageParam param) {
        QueryWrapper<CourtOrder> wrapper = new QueryWrapper<>();
        IPage<CourtOrder> page = new Page<>(param.getCurrentPage(),param.getPageSize());
        if (param.getCurrentUserRoleId() == null || param.getCurrentUserRoleId()>2){
            wrapper.eq("user_id",param.getCurrentUserId());
        }
        courtOrderMapper.selectPage(page,wrapper);
        List<CourtOrder> courtOrderList = page.getRecords();
        for (CourtOrder courtOrder : courtOrderList) {
            User user = userMapper.selectById(courtOrder.getUserId());
            if (user != null){
                courtOrder.setUserName(user.getUserName());
            } else {
                courtOrder.setUserName("用户已不存在");
            }
        }
        return R.ok("查询成功！",courtOrderList,page.getTotal());
    }

    /**
     * @Description: 删除某行
     * @param id
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R removeInfo(Integer id) {
        int delete = courtOrderMapper.deleteById(id);
        if (delete == 0){
            return R.fail("删除失败！");
        }
        return R.ok("删除成功！");
    }

    /**
     * @Description: 批量删除
     * @param courtOrderList
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R removeList(List<CourtOrder> courtOrderList) {
        int deleteBatchIds = courtOrderMapper.deleteBatchIds(courtOrderList);
        if (deleteBatchIds == 0){
            return R.fail("删除失败！");
        }
        return R.ok("删除成功！共删除了"+deleteBatchIds+"条订单数据");
    }

    /**
     * @Description: 退场
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R beyCourt(Integer id) {
        CourtOrder courtOrder = courtOrderMapper.selectById(id);
        if (Objects.equals("1",courtOrder.getReturned() )){
            return R.fail("该场已退过，请联系管理员修改bug");
        }
        courtOrder.setReturnTime(new Date());
        courtOrder.setReturned("1"); // 设置已归还
        // 更新球场状态 球场表
        Court court = courtMapper.selectById(courtOrder.getCourtId());
        if (court != null){
            court.setCourtStatus("1");
            courtMapper.updateById(court);
        }
        // 订单表
        int update = courtOrderMapper.updateById(courtOrder);
        if (update == 0){
            return R.fail("退场失败！");
        }
        return R.ok("退场成功！");
    }


}

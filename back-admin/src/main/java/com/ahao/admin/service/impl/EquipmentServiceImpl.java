package com.ahao.admin.service.impl;

import com.ahao.admin.mapper.EquipmentMapper;
import com.ahao.admin.param.equipment.EquipmentPageSearch;
import com.ahao.admin.pojo.Equipment;
import com.ahao.admin.service.EquipmentService;
import com.ahao.admin.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: EquipmentServiceImpl
 * @Author: ahao
 * @Date: 2023/5/7 16:35
 **/

@Service
@Slf4j
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    /**
     * @Description: 分页+模糊
     * @param pageSearch
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R likeGetListByPage(EquipmentPageSearch pageSearch) {
        QueryWrapper<Equipment> wrapper = new QueryWrapper<>();
        IPage<Equipment> page = new Page<>(pageSearch.getCurrentPage(),pageSearch.getPageSize());
        if (StringUtils.isNotBlank(pageSearch.getEquipmentName())){
            wrapper.like("equipment_name",pageSearch.getEquipmentName());
        }
        equipmentMapper.selectPage(page,wrapper);
        List<Equipment> equipmentList = page.getRecords();
        return R.ok("查询成功！",page.getRecords(),page.getTotal());
    }

    /**
     * @Description: 保存
     * @param equipment
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R saveInfo(Equipment equipment) {
        String equipmentName = equipment.getEquipmentName();
        QueryWrapper<Equipment> wrapper = new QueryWrapper<>();
        wrapper.eq("equipment_name",equipmentName);
        int count = Math.toIntExact(equipmentMapper.selectCount(wrapper));
        if (count != 0){
            return R.fail("已存在该器材，请前往修改添加库存！");
        }
        int insert = equipmentMapper.insert(equipment);
        if (insert == 0){
            return R.fail("保存失败！");
        }
        return R.ok("保存成功！");
    }

    /**
     * @Description: 修改
     * @param equipment
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R updateInfo(Equipment equipment) {
        int update = equipmentMapper.updateById(equipment);
        if (update == 0){
            return R.fail("修改失败！");
        }
        return R.ok("修改成功！");
    }

    /**
     * @Description: 删除
     * @param id
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R removeInfo(Integer id) {
        int delete = equipmentMapper.deleteById(id);
        if (delete == 0){
            return R.fail("删除失败！");
        }
        return R.ok("删除成功！");
    }




}

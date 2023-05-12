package com.ahao.admin.service.impl;

import com.ahao.admin.mapper.CarouselMapper;
import com.ahao.admin.pojo.Carousel;
import com.ahao.admin.service.CarouselService;
import com.ahao.admin.utils.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: CarouselServiceImpl
 * @Author: ahao
 * @Date: 2023/5/8 18:46
 **/

@Service
@Slf4j
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    /**
     * @Description: 获取列表
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R getList() {
        return R.ok("查询成功！",carouselMapper.selectList(null));
    }




}

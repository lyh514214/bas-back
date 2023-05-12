package com.ahao.admin.controller.carousel;

import com.ahao.admin.service.CarouselService;
import com.ahao.admin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: CarouselController
 * @Author: ahao
 * @Date: 2023/5/8 18:48
 **/

@RestController
@RequestMapping("carousel")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    /**
     * @Description: 获取列表
     * @return com.ahao.admin.utils.R
    **/
    @GetMapping("getList")
    public R getList(){
        return carouselService.getList();
    }
}

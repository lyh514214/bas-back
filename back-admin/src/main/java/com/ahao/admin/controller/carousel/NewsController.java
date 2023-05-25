package com.ahao.admin.controller.carousel;

import com.ahao.admin.pojo.News;
import com.ahao.admin.service.NewsService;
import com.ahao.admin.utils.PageParams;
import com.ahao.admin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: CarouselController
 * @Author: ahao
 * @Date: 2023/5/8 18:48
 **/

@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    /**
     * @Description: 获取列表
     * @return com.ahao.admin.utils.R
    **/
    @GetMapping("getList")
    public R getList(){
        return newsService.getList();
    }

    @PostMapping("getListByPage")
    public R getListByPage(@RequestBody PageParams params){
        return newsService.getListByPage(params);
    }

    @PostMapping("saveInfo")
    public R saveInfo(@RequestBody News news){
        return newsService.saveInfo(news);
    }

    @PostMapping("removeInfo")
    public R removeInfo(@RequestParam("news_id") Integer id){
        return newsService.removeInfo(id);
    }

    @PostMapping("updateInfo")
    public R updateInfo(@RequestBody News news){
        return newsService.updateInfo(news);
    }
}

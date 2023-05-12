package com.ahao.admin.controller.court;

import com.ahao.admin.pojo.CourtCategory;
import com.ahao.admin.service.CourtCategoryService;
import com.ahao.admin.utils.PageParams;
import com.ahao.admin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: CourtCategoryController
 * @Author: ahao
 * @Date: 2023/5/3 17:13
 **/

@RestController
@RequestMapping("courtCategory")
public class CourtCategoryController {

    @Autowired
    private CourtCategoryService courtCategoryService;

    /**
     * @Description: 获取列表
     * @return com.ahao.admin.utils.R
    **/
    @GetMapping("getList")
    public R getList(){
        return courtCategoryService.getList();
    }

    /**
     * @Description: 分页
     * @param pageParams
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("byPage")
    public R byPage(@RequestBody PageParams pageParams){
        return courtCategoryService.byPage(pageParams);
    }

    /**
     * @Description: 新增
     * @param courtCategory
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("saveInfo")
    public R saveInfo(@RequestBody CourtCategory courtCategory){
        return courtCategoryService.saveInfo(courtCategory);
    }

    /**
     * @Description: 修改
     * @param courtCategory
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("updateInfo")
    public R updateInfo(@RequestBody CourtCategory courtCategory){
        return courtCategoryService.updateInfo(courtCategory);
    }

    /**
     * @Description: 删除
     * @param courtCategoryId
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("removeInfo")
    public R removeInfo(@RequestParam("court_category_id") Integer courtCategoryId){
        return courtCategoryService.removeInfo(courtCategoryId);
    }

}

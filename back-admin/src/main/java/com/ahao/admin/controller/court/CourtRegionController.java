package com.ahao.admin.controller.court;

import com.ahao.admin.pojo.CourtRegion;
import com.ahao.admin.service.CourtRegionService;
import com.ahao.admin.utils.PageParams;
import com.ahao.admin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: CourtRegionController
 * @Author: ahao
 * @Date: 2023/5/3 17:17
 **/

@RestController
@RequestMapping("courtRegion")
public class CourtRegionController {

    @Autowired
    private CourtRegionService courtRegionService;

    /**
     * @Description: 获取列表
     * @return com.ahao.admin.utils.R
    **/
    @GetMapping("getList")
    public R getList(){
        return courtRegionService.getList();
    }

    /**
     * @Description: 分页
     * @param pageParams
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("byPage")
    public R byPage(@RequestBody PageParams pageParams){
        return courtRegionService.byPage(pageParams);
    }

    /**
     * @Description: 新增
     * @param courtRegion
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("saveInfo")
    public R saveInfo(@RequestBody CourtRegion courtRegion){
        return courtRegionService.saveInfo(courtRegion);
    }

    /**
     * @Description: 修改
     * @param courtRegion
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("updateInfo")
    public R updateInfo(@RequestBody CourtRegion courtRegion){
        return courtRegionService.updateInfo(courtRegion);
    }

    /**
     * @Description: 删除
     * @param courtRegionId
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("removeInfo")
    public R removeInfo(@RequestParam("court_region_id") Integer courtRegionId){
        return courtRegionService.removeInfo(courtRegionId);
    }

    /**
     * @Description: 获取某条区域信息
     * @param courtRegionId
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("getOne")
    public R getOne(@RequestParam("court_region_id") Integer courtRegionId){
        return courtRegionService.getOne(courtRegionId);
    }



}

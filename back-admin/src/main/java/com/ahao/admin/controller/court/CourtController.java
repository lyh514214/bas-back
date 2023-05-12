package com.ahao.admin.controller.court;

import com.ahao.admin.param.court.CourtPageAndSearch;
import com.ahao.admin.pojo.Court;
import com.ahao.admin.service.CourtService;
import com.ahao.admin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: CourtController
 * @Author: ahao
 * @Date: 2023/5/3 16:22
 **/

@RestController
@RequestMapping("court")
public class CourtController {

    @Autowired
    private CourtService courtService;

    /**
     * @Description: 列表
     * @return com.ahao.admin.utils.R
    **/
    @GetMapping("getList")
    public R getList(){
        return courtService.getList();
    }

    /**
     * @Description: 分页+模糊
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("likeGetListByPage")
    public R likeGetListByPage(@RequestBody CourtPageAndSearch courtPageAndSearch){
        return courtService.likeGetListByPage(courtPageAndSearch);
    }

    /**
     * @Description: 新增
     * @param court
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("saveCourtInfo")
    public R saveCourtInfo(@RequestBody Court court){
        return courtService.saveCourtInfo(court);
    }

    /**
     * @Description: 删除
     * @param courtId
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("removeCourtInfo")
    public R removeCourtInfo(@RequestParam("court_id") Integer courtId){
        return courtService.removeCourtInfo(courtId);
    }

    /**
     * @Description: 修改
     * @param court
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("updateCourtInfo")
    public R updateCourtInfo(@RequestBody Court court){
        return courtService.updateCourtInfo(court);
    }

    /**
     * @Description: 根据区域进行租赁
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("getListByRegion")
    public R getListByRegion(@RequestParam("court_region_id") Integer regionId){
        return courtService.getListByRegion(regionId);
    }

}

package com.ahao.admin.controller.user;

import com.ahao.admin.param.role.RolePageAndSearch;
import com.ahao.admin.pojo.Role;
import com.ahao.admin.service.RoleService;
import com.ahao.admin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: RoleController
 * @Author: ahao
 * @Date: 2023/4/28 23:51
 **/

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * @Description: 获取角色列表
     * @return com.ahao.admin.utils.R
     **/
    @GetMapping("getRoleList")
    public R getRoleList(){
        return roleService.getRoleList();
    }

    /**
     * @Description: 分页+模糊查询
     * @param rolePageAndSearch
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("likeGetListByPage")
    public R likeGetListByPage(@RequestBody RolePageAndSearch rolePageAndSearch){
        return roleService.likeGetListByPage(rolePageAndSearch);
    }

    /**
     * @Description: 新增
     * @param role
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("saveRoleInfo")
    public R saveRoleInfo(@RequestBody Role role){
        return roleService.saveRoleInfo(role);
    }

    /**
     * @Description: 修改
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("updateInfo")
    public R updateInfo(@RequestBody Role role){
        return roleService.updateInfo(role);
    }

    /**
     * @Description: 删除
     * @param id
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("removeInfo")
    public R removeInfo(@RequestParam("role_id") Integer id){
        return roleService.removeInfo(id);
    }



}

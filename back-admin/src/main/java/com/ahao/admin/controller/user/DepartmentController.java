package com.ahao.admin.controller.user;

import com.ahao.admin.param.department.DeptPageAndSearch;
import com.ahao.admin.pojo.Department;
import com.ahao.admin.service.DepartmentService;
import com.ahao.admin.service.UserService;
import com.ahao.admin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 部门控制层
 * @Author: ahao
 * @Date: 2023/4/22 11:27
 **/

@RestController
@RequestMapping("dept")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private UserService userService;

    /**
     * @Description: 查询 部门列表
     * @return com.ahao.admin.utils.R
    **/
    @GetMapping("getList")
    public R getList(){
        return departmentService.getList();
    }

    /**
     * @Description: 分页+模糊查询
     * @param deptPageAndSearch 当前页、页容量、部门名称
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("likeGetListByPage")
    public R likeGetListByPage(@RequestBody DeptPageAndSearch deptPageAndSearch){
        return departmentService.likeGetListByPage(deptPageAndSearch);
    }

    /**
     * @Description: 添加 部门
     * @param department  部门信息
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("saveDeptInfo")
    public R saveDeptInfo(@RequestBody Department department){
        return departmentService.saveDeptInfo(department);
    }

    /**
     * @Description: 删除 部门
     * @param deptId 部门id
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("removeUserInfo")
    public R removeUserInfo(@RequestParam("dept_id") Integer deptId){
        return departmentService.removeUserInfo(deptId);
    }

    /**
     * @Description: 修改 部门
     * @param department 部门信息
     * @return com.ahao.admin.utils.R
    **/
    @PostMapping("updateDeptInfo")
    public R updateDeptInfo(@RequestBody Department department){
        return departmentService.updateDeptInfo(department);
    }





}

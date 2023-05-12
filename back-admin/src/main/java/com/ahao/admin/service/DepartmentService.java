package com.ahao.admin.service;

import com.ahao.admin.param.department.DeptPageAndSearch;
import com.ahao.admin.pojo.Department;
import com.ahao.admin.utils.R;

/**
 * @Description: 部门实现接口
 * @Author: ahao
 * @Date: 2023/4/22 11:38
 **/

public interface DepartmentService{

    R getList();

    R likeGetListByPage(DeptPageAndSearch deptPageAndSearch);

    R saveDeptInfo(Department department);

    R removeUserInfo(Integer deptId);

    R updateDeptInfo(Department department);
}

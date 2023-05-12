package com.ahao.admin.service;

import com.ahao.admin.param.role.RolePageAndSearch;
import com.ahao.admin.pojo.Role;
import com.ahao.admin.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: RoleService
 * @Author: ahao
 * @Date: 2023/4/28 22:07
 **/

public interface RoleService extends IService<Role> {

    R getRoleList();

    R likeGetListByPage(RolePageAndSearch rolePageAndSearch);

    R saveRoleInfo(Role role);

    R updateInfo(Role role);

    R removeInfo(Integer id);
}

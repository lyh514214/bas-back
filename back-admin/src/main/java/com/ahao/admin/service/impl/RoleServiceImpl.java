package com.ahao.admin.service.impl;

import com.ahao.admin.mapper.RoleMapper;
import com.ahao.admin.mapper.UserMapper;
import com.ahao.admin.param.role.RolePageAndSearch;
import com.ahao.admin.pojo.Role;
import com.ahao.admin.pojo.User;
import com.ahao.admin.service.RoleService;
import com.ahao.admin.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 角色实现类
 * @Author: ahao
 * @Date: 2023/4/28 22:07
 **/

@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * @Description: 获取角色列表
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R getRoleList() {
        List<Role> roleList = roleMapper.selectList(null);
        log.info("getRoleList业务结束，结果为{}条", roleList.size());
        return R.ok(roleList);
    }

    /**
     * @Description: 分页+模糊查询
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R likeGetListByPage(RolePageAndSearch rolePageAndSearch) {
        IPage<Role> page = new Page<>(rolePageAndSearch.getCurrentPage(),rolePageAndSearch.getPageSize());
        roleMapper.selectPage(page,null);
        log.info("likeGetListByPage业务完成，结果为{}条",page.getRecords().size());
        return R.ok("查询成功！",page.getRecords(),page.getTotal());
    }

    /**
     * @Description: 新增
     * @param role
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R saveRoleInfo(Role role) {
        Role one = roleMapper.selectOne(new QueryWrapper<Role>().eq("role_name", role.getRoleName()));
        if (one != null){
            return R.fail("该角色已存在");
        }
        int insert = roleMapper.insert(role);
        if (insert == 0 ){
            return R.fail("保存失败！");
        }
        log.info("saveRoleInfo业务完成，结果为{}条",insert);
        return R.ok("保存成功！");
    }

    /**
     * @Description: 修改
     * @param role
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R updateInfo(Role role) {
        Role one = roleMapper.selectOne(new QueryWrapper<Role>().eq("role_name", role.getRoleName()));
        if (one != null){
            return R.fail("该角色已存在");
        }
        int update = roleMapper.updateById(role);
        if (update == 0){
            return R.fail("修改失败！");
        }
        log.info("updateInfo业务完成，结果为{}条",update);
        return R.ok("修改成功！");
    }

    /**
     * @Description: 删除
     * @param id
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R removeInfo(Integer id) {
        List<User> userList = userMapper.selectList(new QueryWrapper<User>().eq("role_id", id));
        for (User user : userList) {
            user.setRoleId(3);
            userMapper.updateById(user);
        }
        int delete = roleMapper.deleteById(id);
        if (delete == 0){
            return R.fail("删除失败！");
        }
        log.info("removeInfo业务完成，结果为{}条",delete);
        return R.ok("删除成功！");
    }


}
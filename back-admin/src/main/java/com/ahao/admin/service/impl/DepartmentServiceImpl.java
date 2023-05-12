package com.ahao.admin.service.impl;

import com.ahao.admin.mapper.DepartmentMapper;
import com.ahao.admin.mapper.UserMapper;
import com.ahao.admin.param.department.DeptPageAndSearch;
import com.ahao.admin.pojo.Department;
import com.ahao.admin.pojo.User;
import com.ahao.admin.service.DepartmentService;
import com.ahao.admin.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 部门业务实现类
 * @Author: ahao
 * @Date: 2023/4/22 11:39
 **/

@Service
@Slf4j
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @Description: 获取 部门列表
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R getList() {
        List<Department> departmentList = this.list();
        log.info("getList业务结束，获取部门列表成功！");
        return R.ok(departmentList);
    }

    /**
     * @Description: 分页+模糊查询
     * @param deptPageAndSearch  当前页、页容量、部门id
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R likeGetListByPage(DeptPageAndSearch deptPageAndSearch) {
        Integer currentPage = deptPageAndSearch.getCurrentPage();
        Integer pageSize = deptPageAndSearch.getPageSize();
        String deptName = deptPageAndSearch.getDeptName();
        IPage<Department> departmentPage = new Page<>(currentPage, pageSize);
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(deptName) && !ObjectUtils.isEmpty(deptName)){
            queryWrapper.like("dept_name",deptName);
        }
        IPage<Department> page = this.page(departmentPage, queryWrapper);
        List<Department> departmentList = page.getRecords();
        long total = page.getTotal();
        log.info("likeGetListByPage业务完成，以#当前页:{} #页容量:{} #部门名称:{} 为条件条件查询结果为{}条",currentPage,pageSize,deptName,departmentList.size());
        return R.ok("获取部门列表成功！",departmentList,total);
    }

    /**
     * @Description: 添加
     * @param department 部门信息
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R saveDeptInfo(Department department) {
        boolean save = this.save(department);
        log.info("saveDeptInfo业务完成，{}保存结果为:{}",department.getDeptName(),save);
        if (save){
            return R.ok("保存部门信息成功！");
        }
        return R.fail("保存用户信息失败，请检查后重试！");
    }

    /**
     * @Description: 删除
     * @param deptId  部门id
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R removeUserInfo(Integer deptId) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("dept_id",deptId);
        Long userCount = userMapper.selectCount(userQueryWrapper);
        if (userCount>0){
            return R.fail("该部门还有用户，不能删除");
        }
        boolean remove = this.removeById(deptId);
        log.info("removeUserInfo业务结束，删除#id{}结果为{}",deptId,remove);
        if (remove){
            return R.ok("删除成功！");
        }
        return R.fail("删除失败，请稍后重试！");
    }

    /**
     * @Description: 修改
     * @param department  部门信息
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R updateDeptInfo(Department department) {
        boolean update = this.updateById(department);
        log.info("updateDeptInfo业务完成，{}部门信息修改结果为{}",department.getDeptName(),update);
        if (update){
            return R.ok("修改成功！");
        }
        return R.fail("修改失败！");
    }


}

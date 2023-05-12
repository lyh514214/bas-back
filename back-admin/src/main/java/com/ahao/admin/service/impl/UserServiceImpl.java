package com.ahao.admin.service.impl;

import com.ahao.admin.mapper.DepartmentMapper;
import com.ahao.admin.mapper.RoleMapper;
import com.ahao.admin.mapper.UserMapper;
import com.ahao.admin.param.login.LoginParam;
import com.ahao.admin.param.login.RegisterParam;
import com.ahao.admin.param.user.UserPageAndSearch;
import com.ahao.admin.pojo.Department;
import com.ahao.admin.pojo.Role;
import com.ahao.admin.pojo.User;
import com.ahao.admin.service.UserService;
import com.ahao.admin.utils.MD5Util;
import com.ahao.admin.utils.R;
import com.ahao.admin.utils.Md5User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @Description: UserServiceImpl
 * @Author: ahao
 * @Date: 2023/4/13 22:57
 **/

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * @Description: 获取有个用户信息
     * @param id
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R getOne(Integer id) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_id", id));
        if (user == null){
            return R.fail("获取信息失败！");
        }
        Integer createId = user.getCreateBy();
        Integer updateId = user.getUpdateBy();
        Integer deptId = user.getDeptId();
        Integer roleId = user.getRoleId();
        User createBy = userMapper.selectOne(new QueryWrapper<User>().eq("user_id", createId));
        if (createBy != null){
            user.setCreateByName(createBy.getUserName());
        }
        User updateBy = userMapper.selectOne(new QueryWrapper<User>().eq("user_id", updateId));
        if (updateBy != null){
            user.setUpdateByName(updateBy.getUserName());
        }
        Department department = departmentMapper.selectOne(new QueryWrapper<Department>().eq("dept_id", deptId));
        if (department != null){
            user.setDeptName(department.getDeptName());
        }
        Role role = roleMapper.selectOne(new QueryWrapper<Role>().eq("role_id", roleId));
        if (role != null){
            user.setRoleName(role.getRoleName());
        }
        return R.ok("获取信息成功！",user);
    }

    /**
     * @Description: 模糊 + 分页查询
     * @param userPageAndSearch 当前页、页容量、昵称、用户名
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R likeGetListByPage(UserPageAndSearch userPageAndSearch) {
        Integer currentPage = userPageAndSearch.getCurrentPage();
        Integer pageSize = userPageAndSearch.getPageSize();
        String userName = userPageAndSearch.getUserName();
        String nickName = userPageAndSearch.getNickName();
        IPage<User> page = new Page<>(currentPage,pageSize);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("user_name",userName);
        wrapper.like("nick_name",nickName);
        userMapper.selectPage(page, wrapper);
        List<User> userList = page.getRecords();
        idToDeptName(userList);   //部门id  ==  名称
        idToName(userList);       //用户id  ==  昵称
        idToRoleName(userList);     //角色id  ==  名称
        long total = page.getTotal();
        log.info("likeGetListByPa业务结束,以#当前页:{} #页容量:{} #昵称:{} #账号:{} 为条件条件查询结果为{}条",currentPage,userPageAndSearch.getPageSize(),userPageAndSearch.getNickName(),userPageAndSearch.getUserName(),total);
        return R.ok("搜索成功！",userList, total);
    }
    // 部门id => 部门名称
    private void idToDeptName(List<User> userList){
        List<Department> departmentList = departmentMapper.selectList(null);  // 获取部门列表
        HashMap<Integer, String> map = new HashMap<>();
        for (Department department : departmentList) {
            map.put(department.getDeptId(),department.getDeptName());
        }
        for (User user : userList) {
            user.setDeptName(map.get(user.getDeptId()));
        }
    }
    // 用户id => 角色名称
    private void idToRoleName(List<User> userList){
        List<Role> roleList = roleMapper.selectList(null);
        HashMap<Integer, String> map = new HashMap<>();
        for (Role role : roleList) {
            map.put(role.getRoleId(),role.getRoleName());
        }
        for (User user : userList) {
            user.setRoleName(map.get(user.getRoleId()));
        }
    }
    // 用户id => 用户昵称
    private void idToName(List<User> userList){
        List<User> allList = userMapper.selectList(null);   //获取昵称列表
        HashMap<Integer, String> map = new HashMap<>();
        for (User user : allList) {
            map.put(user.getUserId(),user.getNickName());
        }
        for (User user : userList) {
            user.setCreateByName(map.get(user.getCreateBy()));
            user.setUpdateByName(map.get(user.getUpdateBy()));
        }
    }

    /**
     * @Description: 更新账号状态
     * @param userId 用户id
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R updateStatus(Integer userId , Boolean status , Integer updateBy) {
        User user = new User();
        user.setUserId(userId);
        user.setUpdateBy(updateBy);
        if (!status) {
            user.setStatus("0");
        }else {
            user.setStatus("1");
        }
        int i = userMapper.updateById(user);
        if (i == 0){
            return R.fail("状态更新失败！请稍后重试。");
        }
        log.info("updateStatus业务完成，结果为{}条",i);
        return R.ok("状态更新成功！");
    }

    /**
     * @Description: 添加用户信息
     * @param user  用户信息
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R saveUserInfo(User user) {
        if (userMapper.selectOne(new QueryWrapper<User>().eq("user_name",user.getUserName())) != null){
            return R.fail("已存在相同账号的用户");
        }
        user.setPassword(MD5Util.encode(user.getPassword()+Md5User.USER_SLAT));
        boolean save = this.save(user);
        log.info("saveUserInfo业务结束，结果为{}",save);
        if (save){
            return R.ok("添加成功！");
        }else {
            return R.fail("添加失败,请刷新后重试！");
        }
    }

    /**
     * @Description: 删除用户信息
     * @param userId 用户id
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R removeUserInfo(Integer userId) {
        boolean remove = this.removeById(userId);
        log.info("updateUserInfo业务结束，结果为{}",remove);
        if (remove) {
            return R.ok("删除成功！");
        } else  {
            return R.fail("删除失败，请刷新后重试！");
        }
    }

    /**
     * @Description: 批量删除
     * @param userList  用户集合
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R removeUserList(List<User> userList) {
        boolean remove = this.removeByIds(userList);
        log.info("removeUserList业务完成，删除{}条用户结果为{}",userList.size(),remove);
        if (remove){
            return R.ok("批量删除成功！");
        } else {
            return R.fail("批量删除失败！");
        }
    }

    /**
     * @Description: 修改用户数据
     * @param user 用户参数
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R updateUserInfo(User user) {
        User password = userMapper.selectOne(new QueryWrapper<User>().eq("user_id", user.getUserId()));
        if (!Objects.equals(password.getPassword(), user.getPassword())){
            user.setPassword(MD5Util.encode(user.getPassword()+Md5User.USER_SLAT));
        } // 判断是否修改过密码
        boolean update = this.updateById(user);
        log.info("updateUserInfo业务完成，结果为{}",update);
        if (update){
            return R.ok("修改成功！");
        }
        return R.fail("修改失败！");
    }

    /**
     * @Description: 用户登录
     * @param loginParam 账号密码验证码
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R toLogin(LoginParam loginParam) {
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        String _password = MD5Util.encode(password + Md5User.USER_SLAT);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",username);
        wrapper.eq("password",_password);
        User user = userMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(user)){
            return R.fail("账号或密码错误，请检查后重试！");
        }
        if (!user.getStatus()){
            return R.fail("账号已停用，请联系超管解决");
        }
        log.info("toLogin业务完成,用户已登录");
        return R.ok("登录成功!",user);
    }

    /**
     * @Description: 注册
     * @param registerParam 账号、密码
     * @return com.ahao.admin.utils.R
    **/
    @Override
    public R register(RegisterParam registerParam) {
        System.out.println(registerParam);
        String username = registerParam.getUsername();
        String password = registerParam.getPassword();
        QueryWrapper<User> existQW = new QueryWrapper<>();
        existQW.eq("user_name",username);
        Long exist = userMapper.selectCount(existQW);
        if (exist != 0){
            return R.fail("账号已存在！");
        }
        String _password = MD5Util.encode(password + Md5User.USER_SLAT);
        User user = new User();
        user.setUserName(username);
        user.setPassword(_password);
        user.setNickName("无名球王");
        user.setStatus("1");
        user.setCreateBy(1);
        user.setUpdateBy(1);
        user.setCreateTime(new Date());

        boolean save = this.save(user);
        if (!save){
            return R.ok("注册失败！");
        }
        log.info("register业务完成，注册成功！");
        return R.ok("注册成功！");
    }

}

package com.hrt.manage.controller;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrt.commonutils.JwtUtils;
import com.hrt.commonutils.R;
import com.hrt.manage.entity.User;
import com.hrt.manage.entity.security.UserLogin;
import com.hrt.manage.query.UserQuery;
import com.hrt.manage.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hrt
 * @since 2021-08-12
 */
@RestController
@RequestMapping("/manage/user")
public class UserController {
    @Autowired
    private UserService userservice;

    //根据userId查询信息
    @GetMapping("getUser")
    public R getUser(
           @RequestParam("userId") Long userId
    )
    {
        User user=userservice.getByUserId(userId);
        return R.ok().data("userInfo",user);
    }

    //根据userId查询信息,简要，供oss使用
    @GetMapping("getPrimaryInfo")
    public R getPrimaryInfo(
            @RequestParam("userId") Long userId
    )
    {
        User user=userservice.getByUserId(userId);
        String userName=user.getUserName();
        String userTeam=user.getUserTeam();
        String userGroup=user.getUserGroup();

        return R.ok().data("userName",userName)
                .data("userTeam",userTeam).data("userGroup",userGroup);
    }



    //添加用户
    @ApiOperation(value = "新增用户")
    @PostMapping("addUser")
    public R addUser(
            @ApiParam(name = "user", value = "用户对象", required = true)
            @RequestBody User user
    ) {
        userservice.save(user);
        return R.ok();
    }


    //根据userId查询并更新
    @ApiOperation(value = "更新用户")
    @PostMapping("updateUser")
    public R updateByUserId(
            @RequestBody User user
    ) {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        Long userId = user.getUserId();
        userUpdateWrapper.eq("user_id", userId);
        boolean b = userservice.update(user, userUpdateWrapper);

        return b ? R.ok() : R.error();
    }

    //根据userId查询并删除
    @ApiOperation(value = "根据车队id删除用户")
    @DeleteMapping("deleteUser/{userId}")
    public R deleteByUserId(
            @PathVariable Long userId
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        boolean b = userservice.removeByMap(map);
        return b ? R.ok() : R.error();
    }

    //多条件分页查询
    @ApiOperation(value = "分页用户列表")
    @PostMapping("getUserPage/{current}/{limit}")
    public R pageTeacherQuery(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "userQuery", value = "查询对象", required = false)
            @RequestBody UserQuery userQuery
    ) {
        Page<User> page = new Page<>(current, limit);

        userservice.pageQuery(page, userQuery);

        List<User> records = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    //登录
//    @PostMapping("login")
//    public R login(
//            @RequestBody UserLogin userLogin
//    ) {
//        String token = userservice.login(userLogin);
//        return R.ok().data("token", token);
////        return null;
//    }


    //根据token获取用户信息
    @GetMapping("userInfo")
    public R getUserInfo(
            HttpServletRequest httpServletRequest
    ) {
        String userId2 = JwtUtils.getMemberIdByJwtToken(httpServletRequest);
        Long userId = Long.parseLong(userId2);

        User user=userservice.getByUserId(userId);
        return R.ok().data("userInfo", user);
    }

    //根据userId查询并更改密码
    @PostMapping("updatePwd")
    public R updatePwd(
            @RequestBody UserLogin userLogin
    ) {
        Long userId = userLogin.getUserId();
        String pwd = userLogin.getUserPassword();
        String userPassword = DigestUtils.md5DigestAsHex(pwd.getBytes());
        User user = new User();
        user.setUserPassword(userPassword);

        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("user_id", userId);

        boolean b = userservice.update(user, updateWrapper);

        return b ? R.ok() : R.error();
    }


    //登出
    @PostMapping("logout")
    public R logout() {
        return R.ok();
    }
}
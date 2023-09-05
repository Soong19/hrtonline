package com.hrt.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrt.commonutils.JwtUtils;
import com.hrt.manage.entity.User;
import com.hrt.manage.entity.security.UserLogin;
import com.hrt.manage.mapper.UserMapper;
import com.hrt.manage.query.UserQuery;
import com.hrt.manage.service.UserService;
import com.hrt.servicebase.handler.GlobalExceptionHandler;
import com.hrt.servicebase.handler.HRTException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public void pageQuery(Page<User> pageParam, UserQuery userQuery)
    {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        if (userQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        Long userId=userQuery.getUserId();
        String userTeam = userQuery.getUserTeam();
        String userGroup = userQuery.getUserGroup();
        String userName = userQuery.getUserName();
        String begin = userQuery.getBegin();
        String end = userQuery.getEnd();



        if (!StringUtils.isEmpty(userId)) {
            queryWrapper.like("user_id", userId);
        }
        if (!StringUtils.isEmpty(userGroup)) {
            queryWrapper.eq("user_group", userGroup);
        }
        if (!StringUtils.isEmpty(userTeam)) {
            queryWrapper.eq("user_team", userTeam);
        }
        if (!StringUtils.isEmpty(userName)) {
            queryWrapper.like("user_name", userName);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }
        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public String login(UserLogin userLogin) {
        Long userId=userLogin.getUserId();
        String userPassword=userLogin.getUserPassword();
        //为空则直接异常
        if (StringUtils.isEmpty(userId)||StringUtils.isEmpty(userPassword)){
            System.out.println(1);
            throw new HRTException(20001,"登录失败");
        }
        //判断是否匹配
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",userId);

        User user = baseMapper.selectOne(queryWrapper);
        String userName=user.getUserName();

        if (user==null){
            System.out.println(2);
            throw new HRTException(20001,"登录失败");}

        String s = DigestUtils.md5DigestAsHex(userPassword.getBytes());

        if (!s.equals(user.getUserPassword())){
            System.out.println(3);
            throw new HRTException(20001,"登录失败");
        }
        String token= JwtUtils.getJwtToken(userId.toString(),userName);

        return token;
    }
    // 补一个getOne
    @Override
    public User getByUsername(String username) {
        System.out.println("here");
        return getOne(new QueryWrapper<User>().eq("user_name", username));
    }

    @Override
    public User getByUserId(Long userId) {
        return getOne(new QueryWrapper<User>().eq("user_id", userId));
    }


}
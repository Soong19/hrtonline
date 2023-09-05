package com.hrt.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrt.manage.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hrt.manage.entity.security.UserLogin;
import com.hrt.manage.query.UserQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hrt
 * @since 2021-08-12
 */
public interface UserService extends IService<User> {
    void pageQuery(Page<User> pageParam, UserQuery userQuery);
    String login(UserLogin userLogin);
    User getByUsername(String username);
    User getByUserId(Long userId);


}

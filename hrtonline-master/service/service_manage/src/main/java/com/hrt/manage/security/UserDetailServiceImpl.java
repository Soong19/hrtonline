package com.hrt.manage.security;

import com.hrt.manage.entity.User;
import com.hrt.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);

        System.out.println("进入了此方法");
        System.out.println(username);
        // 判断查询到没有
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码不正确");
        }

        String role = user.getUserRole();
        List<GrantedAuthority> auths =
                AuthorityUtils.commaSeparatedStringToAuthorityList(role);

        return new AccountUser(user.getUserId(),
                user.getUserName(),
                new BCryptPasswordEncoder().encode(user.getUserPassword()),
                auths);
    }
}

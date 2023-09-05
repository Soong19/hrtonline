package com.hrt.manage.entity.security;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserLogin {


    private Long userId;
    private String userPassword;
}

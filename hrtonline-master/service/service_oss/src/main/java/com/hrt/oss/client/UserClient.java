package com.hrt.oss.client;

import com.hrt.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "service-manage")
public interface UserClient {

    //根据userId查询信息,简要，供oss使用
    @GetMapping("/manage/user/getPrimaryInfo")
    public R getPrimaryInfo(@RequestParam("userId") Long userId);


}

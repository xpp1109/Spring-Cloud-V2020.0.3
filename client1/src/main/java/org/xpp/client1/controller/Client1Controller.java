package org.xpp.client1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RefreshScope
public class Client1Controller {
    @Value("${client1.configValue:abc}")
    private String client1ConfigValue;
    @GetMapping("getClient1ValueFromConfig")
    public String getClient1ValueFromConfig(){
        System.out.println("从配置中心加载的数据client1ConfigValue=" + client1ConfigValue);
        return client1ConfigValue;
    }
}

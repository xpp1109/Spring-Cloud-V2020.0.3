package org.xpp.client2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xpp.client2.feign.Client1Feign;

@RestController
@RequestMapping
@RefreshScope
public class Client2Controller {
    @Autowired
    private Client1Feign client1Feign;
    @GetMapping("getClient1ValueFromClient2")
    public String getClient1ValueFromClient2(){
        return client1Feign.getClient1ValueFromConfig();
    }
}
